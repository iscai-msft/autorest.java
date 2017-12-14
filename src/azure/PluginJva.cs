// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.
// 

using AutoRest.Core.Extensibility;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using static AutoRest.Core.Utilities.DependencyInjection;

namespace AutoRest.Java.Azure
{
    public sealed class PluginJva : Plugin<IGeneratorSettings, TransformerJva, CodeGeneratorJva, CodeNamerJva, CodeModel>
    {
        public PluginJva()
        {
            Context = new Context
            {
                // inherit base settings
                Context,

                // set code model implementations our own implementations 
                new Factory<Parameter, ParameterJv>(),
                new Factory<PrimaryType, PrimaryTypeJv>()
            };
        }
    }
}