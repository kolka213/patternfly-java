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
package org.patternfly.component.actionlist;

import org.patternfly.component.ComponentType;
import org.patternfly.component.BaseSubComponent;

import elemental2.dom.HTMLDivElement;

import static org.jboss.elemento.Elements.div;
import static org.patternfly.layout.Classes.actionList;
import static org.patternfly.layout.Classes.component;
import static org.patternfly.layout.Classes.item;

public class ActionListItem extends BaseSubComponent<HTMLDivElement, ActionListItem> {

    // ------------------------------------------------------ factory

    public static ActionListItem actionListItem() {
        return new ActionListItem();
    }

    // ------------------------------------------------------ instance

    static final String SUB_COMPONENT_NAME = "ali";

    ActionListItem() {
        super(div().css(component(actionList, item)).element(), ComponentType.ActionList, SUB_COMPONENT_NAME);
    }

    // ------------------------------------------------------ builder

    @Override
    public ActionListItem that() {
        return this;
    }
}
