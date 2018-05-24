﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ARMUri : List<Segment>
    {
        private readonly MethodJvaf method;
        private readonly string rawUrl;
        private FluentConfig fluentConfig;

        public ARMUri(MethodJvaf method)
        {
            this.method = method;
            this.rawUrl = this.FluentConfig.MappedUrl(method.Url);
            this.Init();
        }

        public int IndexOfSegment(string segmentName)
        {
            int e = this.Select((segment, i) => segment.Name.Equals(segmentName) ? i + 1 : -1)
                .FirstOrDefault(i => i != -1);
            return e == 0 ? -1 : e - 1;
        }

        public IEnumerable<Segment> SegmentsAfterProvider
        {
            get
            {
                int index = this.IndexOfSegment("providers");
                if (index == -1)
                {
                    return new List<Segment>();
                }
                else
                {
                    // Skip the "/providers/" and provider "e.g. /Microsoft.Compute/"
                    return this.Skip(index + 2);
                }
            }
        }

        public bool IsSame(ARMUri other)
        {
            var thisUriItr = this.GetEnumerator();
            var otherUriItr = other.GetEnumerator();
            //
            while (thisUriItr.MoveNext() && otherUriItr.MoveNext())
            {
                if (thisUriItr.Current is PositionalSegment)
                {
                    if (otherUriItr.Current is PositionalSegment)
                    {
                        continue;
                    }
                    else
                    {
                        return false;
                    }
                }
                else if (thisUriItr.Current is ParentSegment)
                {
                    if (otherUriItr.Current is ParentSegment)
                    {
                        if (thisUriItr.Current.Name.EqualsIgnoreCase(otherUriItr.Current.Name))
                        {
                            continue;
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
                else if (thisUriItr.Current is ReferenceSegment)
                {
                    if (otherUriItr.Current is ReferenceSegment)
                    {
                        if (thisUriItr.Current.Name.EqualsIgnoreCase(otherUriItr.Current.Name))
                        {
                            continue;
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
                else if (thisUriItr.Current is TerminalSegment)
                {
                    if (otherUriItr.Current is TerminalSegment)
                    {
                        if (thisUriItr.Current.Name.EqualsIgnoreCase(otherUriItr.Current.Name))
                        {
                            continue;
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
            return thisUriItr.MoveNext() == false
                && otherUriItr.MoveNext() == false;
        }

        private void Init()
        {
            if (string.IsNullOrEmpty(this.rawUrl))
            {
                return;
            }
            IEnumerable<string> parts = this.rawUrl.Split("/").Where(part => !string.IsNullOrEmpty(part));
            //
            IEnumerator<string> itr = parts.GetEnumerator();
            if (!itr.MoveNext())
            {
                return;
            }
            //
            int pos = 0;
            // First Segment
            //
            if (itr.Current.StartsWith("{"))
            {
                string name = itr.Current.Trim(new char[] { '{', '}' });
                this.Add(new PositionalSegment(name: name, position: pos)
                {
                    Parameter = this.method.Parameters
                        .OfType<ParameterJv>()
                        .First(p => p.Location == Core.Model.ParameterLocation.Path && p.SerializedName.EqualsIgnoreCase(name))
                });
            }
            else
            {
                this.Add(new ParentSegment(name: itr.Current, position: pos));
            }
            //
            // Intermediate segments
            //
            while (itr.MoveNext())
            {
                pos++;
                //
                if (itr.Current.StartsWith("{"))
                {
                    string name = itr.Current.Trim(new char[] { '{', '}' });
                    if (this.Last() is ParentSegment && ((ParentSegment) this.Last()).Parameter == null)
                    {
                        ParentSegment parentSegment = (ParentSegment)this.Last();
                        parentSegment.Parameter = this.method.Parameters
                            .OfType<ParameterJv>()
                            .First(p => p.Location == Core.Model.ParameterLocation.Path && p.SerializedName.EqualsIgnoreCase(name));
                    }
                    else
                    {
                        this.Add(new PositionalSegment(name: name, position: pos)
                        {
                            Parameter = this.method.Parameters
                                .OfType<ParameterJv>()
                                .First(p => p.Location == Core.Model.ParameterLocation.Path && p.SerializedName.EqualsIgnoreCase(name))
                        });
                    }
                }
                else
                {
                    if (this.Last() is ParentSegment)
                    {
                        ParentSegment parentSegment = (ParentSegment)this.Last();
                        if (parentSegment.Parameter == null)
                        {
                            this.RemoveAt(this.Count - 1);
                            this.Add(new ReferenceSegment(name: parentSegment.Name, 
                                position: parentSegment.Position, 
                                refName: itr.Current));
                        }
                    }
                    this.Add(new ParentSegment(name: itr.Current, position: pos));
                }
            }
            // Last segment special handling
            //
            if (this.Last() is ParentSegment)
            {
                ParentSegment parentSegment = (ParentSegment)this.Last();
                if (parentSegment.Parameter == null)
                {
                    this.RemoveAt(this.Count - 1);
                    this.Add(new TerminalSegment(name: parentSegment.Name, position: parentSegment.Position));
                }
            }
        }

        private FluentConfig FluentConfig
        {
            get
            {
                if (this.fluentConfig == null)
                {
                    this.fluentConfig = FluentConfig.Create();
                }
                return this.fluentConfig;
            }
        }
    }

    /// <summary>
    /// Base type for all segments in the ARM Uri.
    /// </summary>
    public class Segment
    {
        /// <summary>
        /// The name of the segment.
        /// </summary>
        public string Name { get; }
        /// <summary>
        /// Position of the segment in the raw uri
        /// </summary>
        public int Position { get; }

        public Segment(string name, int position)
        {
            this.Name = name;
            this.Position = position;
        }
    }

    /// <summary>
    /// An ARMUri instance can optionally end with one TerminalSegment, such a segment will exists only if last segment
    /// of the raw Uri is not a parameter.
    /// e.g:
    /// For the raw Uri - /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Compute/availabilitySets
    /// there will be a terminal segment with value 'availabilitySets'
    /// //
    /// For the raw Uri - /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Compute/availabilitySets/{availabilitySetName}
    /// there will NOT be a terminal segment since the last raw segment is a parameter.
    /// 
    /// </summary>
    public class TerminalSegment : Segment
    {
        public TerminalSegment(string name, int position) : base(name, position)
        {}
    }

    /// <summary>
    /// Base type for any specialized segment type that contains a parameter
    /// There are two such specialized types ParentSegment and PositionalSegment.
    /// </summary>
    public class SegmentParameter : Segment
    {
        public ParameterJv Parameter { get; set; }

        public SegmentParameter(string name, int position) : base(name, position)
        { }
    }

    /// <summary>
    /// Represents two consecutive segments in the raw uri of the form "parents/{name}"
    /// For the raw Uri - /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Compute/availabilitySets/{availabilitySetName}
    /// the ParentSegments are:
    /// 
    ///     ||    Name                         Parameter                           Position ||
    ///     ==================================================================================
    ///  1. | subscriptions      | ParameterJv instance for {subscriptionId}     | 0
    ///     ----------------------------------------------------------------------------------
    ///  2. | resourceGroups     | ParameterJv instance for {resourceGroupName}  | 2
    ///     ----------------------------------------------------------------------------------
    ///  3. | availabilitySets   | ParameterJv instance for {availabilitySetName}| 6
    ///     ----------------------------------------------------------------------------------
    /// </summary>
    public class ParentSegment : SegmentParameter
    {
        public ParentSegment(string name, int position) : base(name, position)
        { }
    }

    /// <summary>
    /// Represents a parameter in the raw ARM Uri which is not preceded by parent.
    /// For the raw Uri - "/{scope}/providers/Microsoft.EventGrid/eventSubscriptions/{eventSubscriptionName}/{filtered}"
    /// the PositionalSegments are:
    /// 
    ///     || Name           Parameter                              Position   ||
    ///     ======================================================================
    ///  1. |  scope        | ParameterJv instance for {scope}    |  0
    ///     ----------------------------------------------------------------------
    ///  2. |  filtered     | ParameterJv instance for {filtered} |  5
    /// </summary>
    public class PositionalSegment : SegmentParameter
    {
        public PositionalSegment(string name, int position) : base(name, position)
        { }
    }


    /// <summary>
    /// The segments in the ARM Uri those are not ParentSegment, PositionalSegment or TerminalSegment.
    /// For the raw Uri - "/subscriptions/{subscriptionId}/providers/Microsoft.Compute/locations/{location}/publishers/{publisherName}/artifacttypes/vmextension/types/{type}/versions"
    /// the ReferenceSegment are:
    ///     ||    Name                         RefName      Position
    ///     ========================================================
    ///  1. | providers          | Microsoft.Compute     |   2
    ///     --------------------------------------------------------
    ///  2. | Microsoft.Compute  | locations             |   3
    ///     --------------------------------------------------------
    ///  3. | artifacttypes      | vmextension           |   8
    ///     --------------------------------------------------------
    ///  4. | vmextension        | types                 |   9
    ///     --------------------------------------------------------
    /// </summary>
    public class ReferenceSegment : Segment
    {
        public string RefName { get; }

        public ReferenceSegment(string name, int position, string refName) : base(name, position)
        {
            this.RefName = refName;
        }
    }
}
