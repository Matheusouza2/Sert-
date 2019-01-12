package com.sert.editableFields;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ComboBoxEditor;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;

import org.jdesktop.swingx.autocomplete.AbstractAutoCompleteAdaptor;
import org.jdesktop.swingx.autocomplete.AutoCompleteComboBoxEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDocument;
import org.jdesktop.swingx.autocomplete.ComboBoxAdaptor;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

public class AutoCompleteDecoratorCombo {

	public static void decorate(final JComboBox comboBox) {
		decorate(comboBox, ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
	}

	public static void decorate(final JComboBox comboBox, final ObjectToStringConverter stringConverter) {
		boolean strictMatching = !comboBox.isEditable();

		comboBox.setEditable(true);
		
		JTextComponent editorComponent = (JTextComponent) comboBox.getEditor().getEditorComponent();
		final AbstractAutoCompleteAdaptor adaptor = new ComboBoxAdaptor(comboBox);
		final AutoCompleteDocument document = new AutoCompleteDocument(adaptor, strictMatching, stringConverter);
		decorate(editorComponent, document, adaptor);

		final KeyListener keyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {

				if (keyEvent.isActionKey())
					return;
				if (comboBox.isDisplayable() && !comboBox.isPopupVisible()) {
					int keyCode = keyEvent.getKeyCode();

					if (keyCode == KeyEvent.VK_SHIFT || keyCode == KeyEvent.VK_CONTROL || keyCode == KeyEvent.VK_ALT)
						return;
					if (keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_ENTER)
						return;

					if (keyEvent.isAltDown() || keyEvent.isControlDown())
						return;

					comboBox.setPopupVisible(true);
				}
			}
		};
		editorComponent.addKeyListener(keyListener);

		if (stringConverter != ObjectToStringConverter.DEFAULT_IMPLEMENTATION) {
			comboBox.setEditor(new AutoCompleteComboBoxEditor(comboBox.getEditor(), stringConverter));
		}

		comboBox.addPropertyChangeListener("editor", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				ComboBoxEditor editor = (ComboBoxEditor) e.getNewValue();
				if (editor != null && editor.getEditorComponent() != null) {
					if (!(editor instanceof AutoCompleteComboBoxEditor)
							&& stringConverter != ObjectToStringConverter.DEFAULT_IMPLEMENTATION) {
						comboBox.setEditor(new AutoCompleteComboBoxEditor(editor, stringConverter));

					} else {
						decorate((JTextComponent) editor.getEditorComponent(), document, adaptor);
						editor.getEditorComponent().addKeyListener(keyListener);
					}
				}
			}
		});
	}

	public static void decorate(JTextComponent textComponent, AutoCompleteDocument document,
			final AbstractAutoCompleteAdaptor adaptor) {

		textComponent.setDocument(document);

		textComponent.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				adaptor.markEntireText();
			}
		});

		InputMap editorInputMap = textComponent.getInputMap();

		if (document.isStrictMatching()) {

			editorInputMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, 0),
					DefaultEditorKit.selectionBackwardAction);

			editorInputMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0), errorFeedbackAction);
			editorInputMap.put(
					KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK),
					errorFeedbackAction);
		} else {
			ActionMap editorActionMap = textComponent.getActionMap();

			editorInputMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, 0), "nonstrict-backspace");
			editorActionMap.put("nonstrict-backspace",
					new NonStrictBackspaceAction(editorActionMap.get(DefaultEditorKit.deletePrevCharAction),
							editorActionMap.get(DefaultEditorKit.selectionBackwardAction), adaptor));
		}
	}

	static class NonStrictBackspaceAction extends TextAction {
		Action backspace;
		Action selectionBackward;
		AbstractAutoCompleteAdaptor adaptor;

		public NonStrictBackspaceAction(Action backspace, Action selectionBackward,
				AbstractAutoCompleteAdaptor adaptor) {
			super("nonstrict-backspace");
			this.backspace = backspace;
			this.selectionBackward = selectionBackward;
			this.adaptor = adaptor;
		}

		public void actionPerformed(ActionEvent e) {
			if (adaptor.listContainsSelectedItem()) {
				selectionBackward.actionPerformed(e);
			} else {
				backspace.actionPerformed(e);
			}
		}
	}

	static Object errorFeedbackAction = new TextAction("provide-error-feedback") {
		public void actionPerformed(ActionEvent e) {
			UIManager.getLookAndFeel().provideErrorFeedback(getTextComponent(e));
		}
	};
}