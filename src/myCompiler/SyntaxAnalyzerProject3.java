import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;

public class JavaSyntaxAnalyzerGUI extends JFrame {
    private JTextArea outputTextArea;

    public JavaSyntaxAnalyzerGUI() {
        setTitle("Java Syntax Analyzer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        JButton openFileButton = new JButton("Open Java Source File");
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    public boolean accept(File f) {
                        return f.getName().toLowerCase().endsWith(".java") || f.isDirectory();
                    }

                    public String getDescription() {
                        return "Java Files (*.java)";
                    }
                });
                int result = fileChooser.showOpenDialog(JavaSyntaxAnalyzerGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    analyzeJavaSourceCode(selectedFile);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openFileButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
    }

    private void analyzeJavaSourceCode(File file) {
        // Prepare to capture diagnostics
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();

        // Get the system Java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // Create a file manager to compile the source file
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);

        // Prepare the source file for compilation
        List<File> files = new ArrayList<>();
        files.add(file);

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(files);

        // Compile the source file
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, null, null, compilationUnits);
        boolean success = task.call();

        // Check for syntax errors
        if (!success) {
            outputTextArea.setText("Syntax errors found in the Java source code:\n");
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnosticCollector.getDiagnostics()) {
                outputTextArea.append(diagnostic.getMessage(null) + "\n");
            }
        } else {
            outputTextArea.setText("No syntax errors found in the Java source code.");
        }

        try {
            fileManager.close();
        } catch (IOException e) {
            outputTextArea.append("Error: Could not close file manager.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JavaSyntaxAnalyzerGUI gui = new JavaSyntaxAnalyzerGUI();
            gui.setVisible(true);
        });
    }
}
