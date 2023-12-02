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
package org.patternfly.component.code;

import org.patternfly.component.ComponentType;
import org.patternfly.component.BaseSubComponent;
import org.patternfly.component.button.Button;
import org.patternfly.component.icon.InlineIcon;
import org.patternfly.core.Aria;
import org.patternfly.core.WithIcon;
import org.patternfly.handler.ComponentHandler;
import org.patternfly.layout.PredefinedIcon;

import elemental2.dom.HTMLElement;

import static org.jboss.elemento.EventType.click;
import static org.patternfly.component.ComponentStore.lookupComponent;
import static org.patternfly.component.button.Button.button;
import static org.patternfly.dom.DomGlobal.navigator;
import static org.patternfly.layout.PredefinedIcon.copy;

public class CodeEditorAction extends BaseSubComponent<HTMLElement, CodeEditorAction> implements
        WithIcon<HTMLElement, CodeEditorAction> {

    // ------------------------------------------------------ factory

    public static CodeEditorAction codeEditorAction(String iconClass) {
        return new CodeEditorAction(button().icon(iconClass).control());
    }

    public static CodeEditorAction codeEditorAction(PredefinedIcon predefinedIcon) {
        return new CodeEditorAction(button().icon(predefinedIcon).control());
    }

    public static CodeEditorAction codeEditorAction(InlineIcon icon) {
        return new CodeEditorAction(button().icon(icon).control());
    }

    /**
     * Adds an action. The button should have the modifiers {@link Button#control()}.
     */
    public static CodeEditorAction codeEditorAction(Button button) {
        return new CodeEditorAction(button);
    }

    public static CodeEditorAction codeEditorCopyToClipboardAction() {
        return new CodeEditorAction(button().icon(copy).control())
                .ariaLabel("Copy to clipboard")
                .onClick((event, codeEditor) -> navigator.clipboard.writeText(codeEditor.code()));
    }

    // ------------------------------------------------------ instance

    static final String SUB_COMPONENT_NAME = "cea";

    private final Button button;
    private ComponentHandler<CodeEditorAction> handler;

    CodeEditorAction(Button button) {
        super(button.element(), ComponentType.CodeEditor, SUB_COMPONENT_NAME);
        this.button = button;
    }

    // ------------------------------------------------------ builder

    @Override
    public CodeEditorAction icon(InlineIcon icon) {
        button.icon(icon);
        return this;
    }

    @Override
    public CodeEditorAction that() {
        return this;
    }

    // ------------------------------------------------------ aria

    public CodeEditorAction ariaLabel(String label) {
        if (button != null) {
            button.aria(Aria.label, label);
        }
        return this;
    }

    // ------------------------------------------------------ events

    public CodeEditorAction onClick(ComponentHandler<CodeEditor> handler) {
        button.on(click, e -> handler.handle(e, lookupComponent(ComponentType.CodeEditor, element())));
        return this;
    }
}
