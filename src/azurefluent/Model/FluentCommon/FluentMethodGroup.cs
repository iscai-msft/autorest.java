// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using Pluralize.NET;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentMethodGroup
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public FluentMethodGroup(FluentMethodGroups fluentMethodGroups, string localName)
        {
            if (fluentMethodGroups == null)
            {
                throw new ArgumentNullException(nameof(fluentMethodGroups));
            }
            this.FluentMethodGroups = fluentMethodGroups;
            //
            if (localName == null)
            {
                throw new ArgumentNullException(nameof(localName));
            }
            this.LocalNameInPascalCase = $"{localName.First().ToString().ToUpper()}{localName.Substring(1)}";
            //
            this.ParentMethodGroupNames = new List<string>();
            this.Level = -1;
            this.innerMethods = new List<MethodJvaf>();
            this.childFluentMethodGroups = new List<FluentMethodGroup>();
            this.otherMethods = null;
        }

        public FluentMethodGroup(FluentMethodGroups fluentMethodGroups, string localName, List<string> parentMethodGroupNames)
        {
            if (fluentMethodGroups == null)
            {
                throw new ArgumentNullException(nameof(fluentMethodGroups));
            }
            this.FluentMethodGroups = fluentMethodGroups;
            //
            if (localName == null)
            {
                throw new ArgumentNullException(nameof(localName));
            }
            this.LocalNameInPascalCase = $"{localName.First().ToString().ToUpper()}{localName.Substring(1)}";
            //
            if (parentMethodGroupNames == null)
            {
                throw new ArgumentNullException(nameof(parentMethodGroupNames));
            }
            this.ParentMethodGroupNames = parentMethodGroupNames;
            //
            this.Level = this.ParentMethodGroupNames.Count(); // Level is zero indexed (level of this group in ARM URI)
            //
            this.innerMethods = new List<MethodJvaf>();
            this.childFluentMethodGroups = new List<FluentMethodGroup>();
            this.otherMethods = null;
        }

        /// <summary>
        /// Given an ARM operation endpoint url derive a fluent method group that the operation can possibly belongs to.
        /// </summary>
        /// <param name="fluentMethodGroups">the dict holding fluent method groups</param>
        /// <param name="innerMethod">inner Swagger method</param>
        /// <param name="segments">the ARM operation endpoint url segments (those appear after provider name)</param>
        /// <returns>The fluent method group</returns>
        public static FluentMethodGroup ResolveFluentMethodGroup(FluentMethodGroups fluentMethodGroups,
            MethodJvaf innerMethod,
            IEnumerable<Segment> segments,
            string defaultMethodGroupName)
        {
            List<String> fluentMethodGroupNamesInSegments = new List<String>();
            Pluralizer pluralizer = new Pluralizer();
            HttpMethod httpMethod = innerMethod.HttpMethod;

            segments
            .Where(segment => !(segment is PositionalSegment) && Utils.IsPlural(segment.Name, fluentMethodGroups.FluentConfig))
            .ForEach(segment =>
            {
                fluentMethodGroupNamesInSegments.Add(segment.Name);
            });
            //
            if (fluentMethodGroupNamesInSegments.Count() == 0)
            {
                // Level 0 "Fluent Method Group"
                return new FluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                    localName: defaultMethodGroupName,
                    parentMethodGroupNames: new List<string>());
            }
            if (fluentMethodGroupNamesInSegments.Count() == 1)
            {
                // Level 0 "Fluent Method Group"
                return new FluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                    localName: fluentMethodGroupNamesInSegments[0],
                    parentMethodGroupNames: new List<string>());
            }
            else if (httpMethod == HttpMethod.Post)
            {
                if (segments.Last() is TerminalSegment
                    && segments.Last().Name.EqualsIgnoreCase(fluentMethodGroupNamesInSegments.Last()))
                {
                    //POST /providers/Microsoft.EventHub/namespaces/{nsname}/authorizationRules/{ruleName}/listKeys
                    //
                    return new FluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                        localName: fluentMethodGroupNamesInSegments.SkipLast(1).Last(),
                        parentMethodGroupNames: fluentMethodGroupNamesInSegments.SkipLast(2).ToList());
                }
                else
                {
                    return new FluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                        localName: fluentMethodGroupNamesInSegments.Last(),
                        parentMethodGroupNames: fluentMethodGroupNamesInSegments.SkipLast(1).ToList());
                }
            }
            else
            {
                IModelTypeJv retType = innerMethod.ReturnTypeJva.BodyClientType;
                if ((httpMethod == HttpMethod.Get || httpMethod == HttpMethod.Put) &&
                    (retType is PrimaryType || (retType as SequenceType)?.ElementType is PrimaryType))
                {
                    return new FluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                        localName: fluentMethodGroupNamesInSegments.SkipLast(1).Last(),
                        parentMethodGroupNames: fluentMethodGroupNamesInSegments.SkipLast(2).ToList());
                }
                else
                {
                    return new FluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                        localName: fluentMethodGroupNamesInSegments.Last(),
                        parentMethodGroupNames: fluentMethodGroupNamesInSegments.SkipLast(1).ToList());
                }
            }
        }

        private AncestorsStack ancestorsStack;
        public AncestorsStack AncestorsStack
        {
            get
            {
                if (this.ancestorsStack == null)
                {
                    this.ancestorsStack = new AncestorsStack(this);
                }
                return this.ancestorsStack;
            }
        }

        public FluentMethodGroups FluentMethodGroups { get; private set; }

        public string ManagerName
        {
            get
            {
                return this.FluentMethodGroups.ManagerName;
            }
        }

        public int Level { get; private set; }

        public string JavaInterfaceName
        {
            get; set;
        }

        public string SingularJavaInterfaceName
        {
            get
            {
                if (this.FluentMethodGroups.FluentConfig.IsKnownSingular(JavaInterfaceName))
                {
                    return JavaInterfaceName;
                }
                else
                {
                    Pluralizer pluralizer = new Pluralizer();
                    return pluralizer.Singularize(JavaInterfaceName);
                }
            }
        }

        public string LocalNameInPascalCase
        {
            get; private set;
        }

        private string accessorName;
        public string AccessorMethodName
        {
            get
            {
                if (this.accessorName == null)
                {
                    this.accessorName = this.LocalNameInCamelCase;
                }
                return this.accessorName;
            }
            set
            {
                this.accessorName = value;
            }
        }

        public string LocalNameInCamelCase
        {
            get
            {
                // e.g. virtualMachines
                return this.LocalNameInPascalCase.ToCamelCase();
            }
        }

        public string LocalSingularNameInPascalCase
        {
            get
            {
                if (this.FluentMethodGroups.FluentConfig.IsKnownSingular(LocalNameInPascalCase))
                {
                    return LocalNameInPascalCase;
                }
                else
                {
                    Pluralizer pluralizer = new Pluralizer();
                    // e.g. VirtualMachines -> VirtualMachine
                    return pluralizer.Singularize(LocalNameInPascalCase);
                }
            }
        }

        public string FullyQualifiedName
        {
            get
            {
                String parentsStr = FullyQualifiedParentName;
                if (!String.IsNullOrEmpty(parentsStr))
                {
                    return $"{parentsStr}_{LocalNameInPascalCase}".ToLowerInvariant();
                }
                else
                {
                    return LocalNameInPascalCase.ToLowerInvariant();
                }
            }
        }

        public IReadOnlyList<string> ParentMethodGroupNames { get; private set; }

        public string FullyQualifiedParentName
        {
            get
            {
                String parentsStr = String.Join("_", this.ParentMethodGroupNames);
                if (!String.IsNullOrEmpty(parentsStr))
                {
                    return parentsStr.ToLowerInvariant();
                }
                else
                {
                    return null;
                }
            }
        }

        public string InnerMethodGroupTypeName
        {
            get
            {
                return InnerMethodGroup.MethodGroupImplType;
            }
        }

        public string InnerMethodGroupAccessorName
        {
            get
            {
                return InnerMethodGroup.Name.ToCamelCase();
            }
        }

        public string ExtendsFrom
        {
            get
            {
                List<string> extends = new List<string>();
                extends.AddRange(this.ResourceCreateDescription.MethodGroupInterfaceExtendsFrom);
                extends.AddRange(this.ResourceDeleteDescription.MethodGroupInterfaceExtendsFrom);
                extends.AddRange(this.ResourceGetDescription.MethodGroupInterfaceExtendsFrom);
                extends.AddRange(this.ResourceListingDescription.MethodGroupInterfaceExtendsFrom);
                extends.Add($"HasInner<{this.InnerMethodGroup.MethodGroupImplType}>");

                if (extends.Count() > 0)
                {
                    return $" extends {String.Join(", ", extends)}";
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public HashSet<string> ImportsForInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                imports.AddRange(this.ResourceCreateDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.ResourceDeleteDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.ResourceGetDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.ResourceListingDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.OtherMethods.ImportsForInterface);
                imports.Add($"{this.package}.implementation.{this.InnerMethodGroupTypeName}");
                imports.Add("com.microsoft.azure.arm.model.HasInner");

                return imports;
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                imports.AddRange(this.ModelMapper.Imports);
                return imports;
            }
        }

        private OtherMethods otherMethods;
        public OtherMethods OtherMethods
        {
            get
            {
                if (otherMethods != null)
                {
                    return this.otherMethods;
                }
                else
                {
                    HashSet<string> knownMethodNames = new HashSet<string>();
                    if (ResourceCreateDescription.SupportsCreating)
                    {
                        knownMethodNames.Add(ResourceCreateDescription.CreateMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceUpdateDescription.SupportsUpdating)
                    {
                        knownMethodNames.Add(ResourceUpdateDescription.UpdateMethod.Name.ToLowerInvariant());
                        //
                        FluentMethod updateMethod = ResourceUpdateDescription.UpdateMethod;
                        if (updateMethod.InnerMethod.HttpMethod == HttpMethod.Put)
                        {
                            // If PUT based update is supported then skip any PATCH based update method
                            // being treated as "Other methods".
                            //
                            var patchUpdateMethod = this.InnerMethods
                                .Where(m => m.HttpMethod == HttpMethod.Patch)
                                .Where(m => m.Url.EqualsIgnoreCase(updateMethod.InnerMethod.Url))
                                .FirstOrDefault();
                            if (patchUpdateMethod != null)
                            {
                                knownMethodNames.Add(patchUpdateMethod.Name.ToLowerInvariant());
                            }
                        }
                    }

                    if (ResourceListingDescription.SupportsListByImmediateParent)
                    {
                        knownMethodNames.Add(ResourceListingDescription.ListByImmediateParentMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceListingDescription.SupportsListByResourceGroup)
                    {
                        knownMethodNames.Add(ResourceListingDescription.ListByResourceGroupMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceListingDescription.SupportsListBySubscription)
                    {
                        knownMethodNames.Add(ResourceListingDescription.ListBySubscriptionMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceGetDescription.SupportsGetByImmediateParent)
                    {
                        knownMethodNames.Add(ResourceGetDescription.GetByImmediateParentMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceGetDescription.SupportsGetByResourceGroup)
                    {
                        knownMethodNames.Add(ResourceGetDescription.GetByResourceGroupMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceDeleteDescription.SupportsDeleteByImmediateParent)
                    {
                        knownMethodNames.Add(ResourceDeleteDescription.DeleteByImmediateParentMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceDeleteDescription.SupportsDeleteByResourceGroup)
                    {
                        knownMethodNames.Add(ResourceDeleteDescription.DeleteByResourceGroupMethod.Name.ToLowerInvariant());
                    }
                    this.otherMethods = new OtherMethods(this, knownMethodNames);
                    return this.otherMethods;
                }
            }
        }

        #region InnerMethodGroup

        public MethodGroupJvaf InnerMethodGroup { get; private set; }
        public void SetInnerMethodGroup(MethodGroupJvaf innerMethodGroup)
        {
            this.InnerMethodGroup = innerMethodGroup;
        }

        #endregion

        #region Inner Methods

        private List<MethodJvaf> innerMethods;
        public IReadOnlyList<MethodJvaf> InnerMethods
        {
            get
            {
                return this.innerMethods;
            }
        }

        public void AddInnerMethod(MethodJvaf innerMethod)
        {
            this.innerMethods.Add(innerMethod);
        }

        public void AddInnerMethods(IReadOnlyList<MethodJvaf> innerMethods)
        {
            this.innerMethods.AddRange(innerMethods);
        }

        #endregion

        #region Parent Fluent Method Group

        public FluentMethodGroup ParentFluentMethodGroup { get; private set; }
        public void SetParentFluentMethodGroup(FluentMethodGroup fluentMethodGroup)
        {
            this.ParentFluentMethodGroup = fluentMethodGroup;
        }

        #endregion

        #region Child Fluent Method Groups

        private List<FluentMethodGroup> childFluentMethodGroups;
        public IReadOnlyList<FluentMethodGroup> ChildFluentMethodGroups
        {
            get
            {
                return childFluentMethodGroups;
            }
        }
        public void AddToChildFluentMethodGroup(FluentMethodGroup fluentMethodGroup)
        {
            this.childFluentMethodGroups.Add(fluentMethodGroup);
        }

        #endregion

        private ResourceCreateDescription resourceCreateDescription;
        public ResourceCreateDescription ResourceCreateDescription
        {
            get
            {
                if (this.resourceCreateDescription == null)
                {
                    this.resourceCreateDescription = new ResourceCreateDescription(this);
                }
                return this.resourceCreateDescription;
            }
        }

        private ResourceUpdateDescription resourceUpdateDescription;
        public ResourceUpdateDescription ResourceUpdateDescription
        {
            get
            {
                if (this.resourceUpdateDescription == null)
                {
                    this.resourceUpdateDescription = new ResourceUpdateDescription(this.ResourceCreateDescription, this);
                }
                return this.resourceUpdateDescription;
            }
        }

        private ResourceListingDescription resourceListingDescription;
        public ResourceListingDescription ResourceListingDescription
        {
            get
            {
                if (this.resourceListingDescription == null)
                {
                    this.resourceListingDescription = new ResourceListingDescription(this);
                }
                return this.resourceListingDescription;
            }
        }

        private ResourceGetDescription resourceGetDescription;
        public ResourceGetDescription ResourceGetDescription
        {
            get
            {
                if (this.resourceGetDescription == null)
                {
                    this.resourceGetDescription = new ResourceGetDescription(this);
                }
                return this.resourceGetDescription;
            }
        }

        private ResourceDeleteDescription resourceDeleteDescription;
        public ResourceDeleteDescription ResourceDeleteDescription
        {
            get
            {
                if (this.resourceDeleteDescription == null)
                {
                    this.resourceDeleteDescription = new ResourceDeleteDescription(this);
                }
                return this.resourceDeleteDescription;
            }
        }

        private FluentModel standardFluentModel;
        public FluentModel StandardFluentModel
        {
            get
            {
                if (!this.derivedStandardModel)
                {
                    throw new InvalidOperationException("DeriveFluentModelForMethodGroup requires to be invoked before retriving StandardFluentModel");
                }
                return this.standardFluentModel;
            }
        }

        private NonStandardToStanardModelMappingHelper mapper;
        public NonStandardToStanardModelMappingHelper ModelMapper
        {
            get
            {
                if (this.mapper == null)
                {
                    this.mapper = new NonStandardToStanardModelMappingHelper(this);
                }
                return this.mapper;
            }
        }

        private bool derivedStandardModel;
        public void DeriveStandrdFluentModelForMethodGroup()
        {
            if (this.derivedStandardModel)
            {
                return;
            }

            this.derivedStandardModel = true;

            // Find "ONE" fluent model that can be used across "Standard methods" 
            // 1. (GetByResourceGroup | ListByResourceGroup | ListBySubscription | Create in RG)
            // 2. (GetByImmediateParent | ListByImmediateParent | Create in Parent)
            //
            // Derive an "inner model then a fluent model" that represents the return type of standard methods 
            // in this fluent model. We want all thoses standard methods to return same fluent type though the
            // inner methods can return different inner model types.
            //
            CompositeTypeJvaf standardModelInner = null;
            if (ResourceGetDescription.SupportsGetByResourceGroup)
            {
                standardModelInner = ResourceGetDescription.GetByResourceGroupMethod.InnerReturnType;
            }
            else if (ResourceCreateDescription.SupportsCreating)
            {
                standardModelInner = ResourceCreateDescription.CreateMethod.InnerReturnType;
            }
            else if (ResourceListingDescription.SupportsListByResourceGroup)
            {
                standardModelInner = ResourceListingDescription.ListByResourceGroupMethod.InnerReturnType;
            }
            else if (ResourceListingDescription.SupportsListBySubscription)
            {
                standardModelInner = ResourceListingDescription.ListBySubscriptionMethod.InnerReturnType;
            }
            else if (ResourceGetDescription.SupportsGetByImmediateParent)
            {
                standardModelInner = ResourceGetDescription.GetByImmediateParentMethod.InnerReturnType;
            }
            else if (ResourceListingDescription.SupportsListByImmediateParent)
            {
                standardModelInner = ResourceListingDescription.ListByImmediateParentMethod.InnerReturnType;
            }
            else if (ResourceUpdateDescription.SupportsUpdating)
            {
                standardModelInner = ResourceUpdateDescription.UpdateMethod.InnerReturnType;
            }

            if (standardModelInner != null)
            {
                this.standardFluentModel = new FluentModel(standardModelInner);
            }
        }

        /// <summary>
        /// Checks the method group and it's standard model belongs to groupable category. This will be 
        /// used to decide whether methodgroup can be extends from "GroupableResourcesImpl" and standard
        /// model can implements GroupableResource and extends GroupableResourceImpl.
        /// </summary>
        /// 
        public bool IsGroupableTopLevel
        {
            get
            {
                if (this.Level == 0)
                {
                    if (this.ResourceCreateDescription.SupportsCreating)
                    {
                        // It's guarnteed to be have a standardModel if create is supported
                        //
                        if (this.ResourceCreateDescription.CreateType == CreateType.WithResourceGroupAsParent)
                        {
                            if (Utils.IsTrackedResource(this.StandardFluentModel))
                            {
                                // FModel -> implements GroupableResourceCore extends GroupableResourceCoreImpl
                                // FGroup -> extends GroupableResourcesCoreImpl
                                //
                                return true;
                            }
                            else
                            {
                                // FModel -> implements SupportsCreating
                                // FGroup -> extends CreatableUpdate
                                //
                                // Treat as "NonGroupableTopLevel"
                                return false;
                            }
                        }
                        else
                        {
                            // can be created but not under a resource group, don't consider it as Groupable
                            // even though standard model is a resource.
                            // FModel -> implements SupportsCreating
                            // FGroup -> extends CreatableUpdate
                            //
                            // Treat as "NonGroupableTopLevel"
                            //
                            return false;
                        }
                    }
                    else if (this.ResourceUpdateDescription.SupportsUpdating)
                    {
                        // It's guarnteed to be have a standardModel if update is supported
                        //
                        if (this.ResourceUpdateDescription.UpdateType == UpdateType.WithResourceGroupAsParent)
                        {
                            if (Utils.IsTrackedResource(this.StandardFluentModel))
                            {
                                // FModel -> implements GroupableResourceCore extends GroupableResourceCoreImpl
                                // FGroup -> extends GroupableResourcesCoreImpl
                                //
                                return true;
                            }
                            else
                            {
                                // FModel -> implements Appliable
                                // FGroup -> extends CreatableUpdate
                                //
                                // Treat as "NonGroupableTopLevel"
                                //
                                return false;
                            }
                        }
                        else
                        {
                            // FModel -> implements Appliable
                            // FGroup -> extends CreatableUpdate
                            //
                            // Treat as "NonGroupableTopLevel"
                            //
                            return false;
                        }
                    }
                    else
                    {
                        // Do not support creation or updation then we treat this as Groupable 
                        // only if there is a standard model and it is a Resource.
                        //
                        return Utils.IsTrackedResource(this.StandardFluentModel);
                    }
                }
                return false;
            }
        }

        public bool IsNonGroupableTopLevel
        {
            get
            {
                if (this.Level == 0)
                {
                    if (!this.IsGroupableTopLevel)
                    {
                        return this.StandardFluentModel != null;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
        }

        public bool IsNested
        {
            get
            {
                return this.Level > 0;
            }
        }
    }
}