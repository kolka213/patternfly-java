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
package org.patternfly.component.form;

import java.util.ArrayList;
import java.util.List;

import org.jboss.elemento.Attachable;
import org.jboss.elemento.By;
import org.jboss.elemento.Elements;
import org.jboss.elemento.logger.Logger;
import org.patternfly.component.BaseComponent;
import org.patternfly.component.ComponentType;
import org.patternfly.component.SubComponent;
import org.patternfly.component.help.HelperText;
import org.patternfly.style.Classes;
import org.patternfly.style.Modifiers.Inline;

import elemental2.dom.HTMLElement;
import elemental2.dom.MutationRecord;

import static org.jboss.elemento.Elements.div;
import static org.patternfly.style.Classes.component;
import static org.patternfly.style.Classes.group;
import static org.patternfly.style.Modifiers.toggleModifier;

public class FormGroupControl extends SubComponent<HTMLElement, FormGroupControl> implements
        Inline<HTMLElement, FormGroupControl>,
        Attachable {

    // ------------------------------------------------------ factory

    public static FormGroupControl formGroupControl() {
        return new FormGroupControl();
    }

    // ------------------------------------------------------ instance

    private static final Logger logger = Logger.getLogger(FormGroupControl.class.getName());
    static final String SUB_COMPONENT_NAME = "fgc";

    private FormControl<?, ?> control;
    private final List<Checkbox> checkboxes;
    private final List<Radio> radios;

    FormGroupControl() {
        super(ComponentType.Form, SUB_COMPONENT_NAME, div().css(component(Classes.form, group, Classes.control)).element());
        this.checkboxes = new ArrayList<>();
        this.radios = new ArrayList<>();
        Attachable.register(this, this);
    }

    @Override
    public void attach(MutationRecord mutationRecord) {
        FormGroup formGroup = lookupSubComponent(FormGroup.SUB_COMPONENT_NAME);

        if (formGroup.fieldId != null && control != null && !formGroup.fieldId.equals(control.id)) {
            logger.error("The field id of the form group %o is different from the id of its control %o: '%s' != '%s'",
                    formGroup.element(), element(), formGroup.fieldId, control.id);
        }

        if (formGroup.fieldId != null) {
            for (Checkbox checkbox : checkboxes) {
                checkbox.inputElement().name(formGroup.fieldId);
            }
            for (Radio radio : radios) {
                radio.inputElement().name(formGroup.fieldId);
            }
        }
    }

    // ------------------------------------------------------ add

    public <E extends HTMLElement, B extends BaseComponent<E, B>> FormGroupControl addControl(FormControl<E, B> control) {
        return add(control);
    }

    // override to assure internal wiring
    public <E extends HTMLElement, B extends BaseComponent<E, B>> FormGroupControl add(FormControl<E, B> control) {
        this.control = control;
        return add(control.element());
    }

    public FormGroupControl addCheckbox(Checkbox checkbox) {
        return add(checkbox);
    }

    // override to assure internal wiring
    public FormGroupControl add(Checkbox checkbox) {
        checkboxes.add(checkbox);
        return add(checkbox.element());
    }

    public FormGroupControl addRadio(Radio radio) {
        return add(radio);
    }

    // override to assure internal wiring
    public FormGroupControl add(Radio radio) {
        radios.add(radio);
        return add(radio.element());
    }

    public FormGroupControl addHelperText(HelperText helperText) {
        return add(helperText);
    }

    public FormGroupControl setHelperText(HelperText helperText) {
        removeHelperText();
        return add(helperText);
    }

    // override to assure internal wiring
    public FormGroupControl add(HelperText helperText) {
        return add(div().css(component(Classes.form, Classes.helperText))
                .add(helperText));
    }

    // ------------------------------------------------------ builder

    /** Same as {@linkplain #stack(boolean) stack(true)} */
    public FormGroupControl stack() {
        return stack(true);
    }

    /** Adds/removes {@linkplain Classes#modifier(String) modifier(stack)} */
    public FormGroupControl stack(boolean stack) {
        return toggleModifier(this, element(), Classes.stack, stack);
    }

    @Override
    public FormGroupControl that() {
        return this;
    }

    // ------------------------------------------------------ api

    public void removeHelperText() {
        for (HTMLElement helperText : findAll(By.classname(component(Classes.form, Classes.helperText)))) {
            Elements.failSafeRemoveFromParent(helperText);
        }
    }
}
