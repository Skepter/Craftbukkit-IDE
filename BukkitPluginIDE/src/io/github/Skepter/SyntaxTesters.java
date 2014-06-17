package io.github.Skepter;

import java.awt.event.ItemEvent;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.text.EditorKit;

import jsyntaxpane.DefaultSyntaxKit;
import jsyntaxpane.SyntaxDocument;
import jsyntaxpane.Token;
import jsyntaxpane.actions.ActionUtils;
import jsyntaxpane.actions.CaretMonitor;

public class SyntaxTesters extends javax.swing.JFrame {

	private static final long serialVersionUID = 6894251044281684080L;
	/** Creates new form Tester */
	public SyntaxTesters() {
		// this is a test for adding regex lexer.  It wont work unless the
		// JavaRegex.properties is found in the classpath
		// DefaultSyntaxKit.registerContentType("text/aa_regex", "de.sciss.syntaxpane.JavaRegexKit");
		initComponents();
		jCmbLangs.setModel(new DefaultComboBoxModel<Object>(DefaultSyntaxKit.getContentTypes()));
		// jEdtTest.setContentType(jCmbLangs.getItemAt(0).toString());
		jCmbLangs.setSelectedItem("text/java");
		new CaretMonitor(jEdtTest, lblCaretPos);
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCaretPos = new javax.swing.JLabel();
        jEdtTest = new javax.swing.JEditorPane();
        jScrollPane1 = new javax.swing.JScrollPane(jEdtTest);
        lblToken = new javax.swing.JLabel();
        jCmbLangs = new javax.swing.JComboBox<Object>();
        jToolBar1 = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("title"); // NOI18N

        lblCaretPos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblCaretPos.setText("text"); // NOI18N

        jEdtTest.setContentType("text/java"); // NOI18N
        jEdtTest.setFont(new java.awt.Font("Monospaced", 0, 13));
        jEdtTest.setCaretColor(new java.awt.Color(153, 204, 255));
        jEdtTest.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jEdtTestCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jEdtTest);

        lblToken.setFont(new java.awt.Font("Courier New", 0, 12));
        lblToken.setText("text"); // NOI18N

        jCmbLangs.setMaximumRowCount(20);
        jCmbLangs.setFocusable(false);
        jCmbLangs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbLangsItemStateChanged(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCmbLangs, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
                .addComponent(lblCaretPos, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblToken, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addGap(484, 484, 484))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblToken, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCaretPos, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jCmbLangs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jEdtTestCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jEdtTestCaretUpdate
		SyntaxDocument sDoc = ActionUtils.getSyntaxDocument(jEdtTest);
		if (sDoc != null) {
			Token t = sDoc.getTokenAt(evt.getDot());
			if (t != null) {
				CharSequence tData = t.getText(sDoc);
				if (t.length > 40) {
					tData = tData.subSequence(0, 40);
				}
				lblToken.setText(t.toString() + ": " + tData);
			} else {
				// null token, remove the status
				lblToken.setText(java.util.ResourceBundle.getBundle("de/sciss/syntaxpane/Bundle").getString("NO_TOKEN_AT_CURSOR"));
			}
		}

    }//GEN-LAST:event_jEdtTestCaretUpdate

    private void jCmbLangsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbLangsItemStateChanged
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			String lang = jCmbLangs.getSelectedItem().toString();

			// save the state of the current JEditorPane, as it's Document is about
			// to be replaced.
			String oldText = jEdtTest.getText();

			// install a new DefaultSyntaxKit on the JEditorPane for the requested language.
			jEdtTest.setContentType(lang);
			// Recreate the Toolbar
			jToolBar1.removeAll();
			EditorKit kit = jEdtTest.getEditorKit();
			if (kit instanceof DefaultSyntaxKit) {
				DefaultSyntaxKit defaultSyntaxKit = (DefaultSyntaxKit) kit;
				defaultSyntaxKit.addToolBarActions(jEdtTest, jToolBar1);
			}
			jToolBar1.validate();
			try {
				// setText should not be called (read the JavaDocs).  Better use the read
				// method and create a new document.
				jEdtTest.read(new StringReader(oldText), lang);
			} catch (IOException ex) {
				Logger.getLogger(SyntaxTesters.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		jEdtTest.requestFocusInWindow();
    }//GEN-LAST:event_jCmbLangsItemStateChanged

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					DefaultSyntaxKit.initKit();
					new SyntaxTesters().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(2);
				}
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> jCmbLangs;
    private javax.swing.JEditorPane jEdtTest;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCaretPos;
    private javax.swing.JLabel lblToken;
    // End of variables declaration//GEN-END:variables
}