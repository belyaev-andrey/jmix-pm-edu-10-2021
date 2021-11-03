com_company_jmixpm_widgets_QuillEditor = function () {
    let connector = this;
    let element = connector.getElement();
    element.innerHTML = "<div id=\"quill-editor\"></div>";

    var quill = new Quill('#quill-editor', {theme: 'snow'});

    quill.on('text-change', function (delta, oldDelta, source) {
        if (source === 'user') {
            connector.valueChanged(quill.getText());
        }
    });

    connector.deleteText = function () {
        quill.deleteText(0, quill.getLength());
    }

    connector.onStateChange = function () {
        let value = connector.getState().value;
        quill.setText(value)
    }
}