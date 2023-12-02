/*
 *  Copyright 2023 Red Hat
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.patternfly.component.popover;

import org.patternfly.component.ComponentType;
import org.patternfly.component.BaseSubComponent;

import elemental2.dom.HTMLDivElement;

import static org.jboss.elemento.Elements.div;
import static org.patternfly.layout.Classes.component;
import static org.patternfly.layout.Classes.footer;
import static org.patternfly.layout.Classes.popover;

public class PopoverFooter extends BaseSubComponent<HTMLDivElement, PopoverFooter> {

    // ------------------------------------------------------ factory

    public static PopoverFooter popoverFooter() {
        return new PopoverFooter();
    }

    // ------------------------------------------------------ instance

    static final String SUB_COMPONENT_NAME = "pf";

    PopoverFooter() {
        super(div().css(component(popover, footer)).element(), ComponentType.Popover, SUB_COMPONENT_NAME);
    }

    // ------------------------------------------------------ builder

    @Override
    public PopoverFooter that() {
        return this;
    }
}
