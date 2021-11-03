package com.company.jmixpm.widgets;

import com.company.jmixpm.widgets.client.QuillEditorState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.event.HasUserOriginated;
import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.ui.AbstractJavaScriptComponent;
import io.jmix.ui.widget.WebJarResource;

import java.util.EventObject;

@WebJarResource({
        "quill:dist/quill.js",
        "quill:dist/quill.snow.css",
})
@JavaScript({
        "vaadin://quill/quill-editor-connector.js"
})
public class QuillEditor extends AbstractJavaScriptComponent {

    private ValueChangeHandler valueChangeHandler;

    public QuillEditor() {
        addFunction("valueChanged", arguments -> {
            String oldValue = getState(false).value;
            getState(false).value = arguments.getString(0);

            fireEvent(createValueChangeEvent(oldValue, true));
        });
    }

    public String getValue() {
        return getState(false).value;
    }

    public void setValue(String value) {
        String oldValue = getState(false).value;
        getState().value = value;

        fireEvent(createValueChangeEvent(oldValue, false));
    }

    private void fireEvent(ValueChangeEvent event) {
        if (valueChangeHandler != null) {
            valueChangeHandler.valueChanged(event);
        }
    }

    public ValueChangeHandler getValueChangeHandler() {
        return valueChangeHandler;
    }

    public void setValueChangeHandler(ValueChangeHandler valueChangeHandler) {
        this.valueChangeHandler = valueChangeHandler;
    }

    public void clear() {
        callFunction("deleteText");
    }

    private ValueChangeEvent createValueChangeEvent(String oldValue, boolean userOriginated) {
        return new ValueChangeEvent(this, getValue(), oldValue, userOriginated);
    }

    public interface ValueChangeHandler {
        void valueChanged(ValueChangeEvent event);
    }

    public class ValueChangeEvent extends EventObject implements HasUserOriginated {

        private String oldValue;
        private String value;

        private boolean userOriginated;

        public ValueChangeEvent(QuillEditor editor,
                                String value, String oldValue, boolean userOriginated) {
            super(editor);

            this.value = value;
            this.oldValue = oldValue;
            this.userOriginated = userOriginated;
        }

        @Override
        public QuillEditor getSource() {
            return (QuillEditor) super.getSource();
        }

        public String getValue() {
            return value;
        }

        public String getOldValue() {
            return oldValue;
        }

        @Override
        public boolean isUserOriginated() {
            return userOriginated;
        }
    }

    @Override
    protected QuillEditorState getState() {
        return (QuillEditorState) super.getState();
    }

    @Override
    protected QuillEditorState getState(boolean markAsDirty) {
        return (QuillEditorState) super.getState(markAsDirty);
    }
}
