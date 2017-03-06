package com.company.app.menu;

import com.company.app.Application;
import com.company.bean.Tab;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileMenu extends JMenu {

    private Application application;
    private JMenuItem newFile, openFile, saveFile, saveAs, close, closeAll, exit;
    private ImageIcon icon;
    private File file;

    /**
     * Create a new FileMenu object
     *
     * @param name FileMenu name
     */
    public FileMenu(String name,Application application) {
        this.application = application;
        this.setText(name);
        icon = new ImageIcon("icons/1.png");
        newFile = new JMenuItem("New", icon);
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });

        newFile.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        this.add(newFile);
        this.addSeparator();

        icon = new ImageIcon("icons/2.png");
        openFile = new JMenuItem("Open...", icon);
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        openFile.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        this.add(openFile);

        icon = new ImageIcon("icons/3.png");
        saveFile = new JMenuItem("Save", icon);
        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (application.getTable().get(application.getSelectedIndex()).getFilePath() == null) {
                        String param = "saveAs";
                        saveFile(param);
                    } else {
                        String param = "saveFile";
                        saveFile(param);
                    }
                } catch (IndexOutOfBoundsException a) {
                    JOptionPane.showMessageDialog(null, "Make a new document first");
                }
            }
        });
        saveFile.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        this.add(saveFile);

        icon = new ImageIcon("icons/4.png");
        saveAs = new JMenuItem("Save As...", icon);
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String param = "saveAs";
                try {
                    saveFile(param);
                } catch (IndexOutOfBoundsException a) {
                    JOptionPane.showMessageDialog(null, "Make a new document first");
                }
            }
        });
        saveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        this.add(saveAs);
        this.addSeparator();

        icon = new ImageIcon("icons/5.png");
        close = new JMenuItem("Close", icon);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        close.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
        this.add(close);

        icon = new ImageIcon("icons/6.png");
        closeAll = new JMenuItem("Close All", icon);
        closeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAll();
            }
        });
        closeAll.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        this.add(closeAll);
        this.addSeparator();

        icon = new ImageIcon("icons/7.png");
        exit = new JMenuItem("Exit", icon);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exit.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        this.add(exit);
    }

    /**
     * Method opens new text window;
     */
    public void open() {
        int index = application.getTable().size();
        Tab tab = new Tab(application);
        application.getTable().add(tab);
        application.getjTabbedPane().addTab("New", null, application.getTable().get(index), null);
        application.getTable().get(index).setViewportView(application.getTable().get(index).getTextArea());
    }

    /**
     * Method opens a new file
     */
    public void openFile() {
        try {
            application.getTable().get(application.getSelectedIndex()).getTextArea().setText("");
            if (application.getTable().size() == 0) {
                open();
            }
            JFileChooser fileChooser = new JFileChooser();
            FileFilter txtFilter = new FileNameExtensionFilter("txt files", "txt");
            fileChooser.setFileFilter(txtFilter);

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                file = fileChooser.getSelectedFile();
                if (checkFileIsOpen(file.getPath()) == true) {
                    JOptionPane.showMessageDialog(null, "Selected file is open already !!!");

                } else {
                    FileReader fileReader = null;
                    BufferedReader bufferedReader = null;
                    StringBuilder stringBuilder = new StringBuilder();
                    try {
                        fileReader = new FileReader(file);
                        bufferedReader = new BufferedReader(fileReader);

                        String line;

                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                            stringBuilder.append("\n");
                            application.getTable().get(application.getSelectedIndex()).getTextArea().setText(stringBuilder.toString());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fileReader.close();
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    application.getjTabbedPane().setTitleAt(application.getSelectedIndex(), file.getName());
                    application.getTable().get(application.getSelectedIndex()).setName(file.getName());
                    application.getTable().get(application.getSelectedIndex()).setFilePath(file.getPath());
                }
            }
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Make a new document first");
        }
    }

    /**
     * Method saves new file
     *
     * @param param - determines first or next save
     */
    public void saveFile(String param) {
        File file = null;
        JFileChooser fileChooser = null;
        String filePath = null;
        boolean haveFilePath = false;

        if (param.equals("saveFile")) {

            filePath = application.getTable().get(application.getSelectedIndex()).getFilePath();
            file = new File(filePath);
            haveFilePath = true;

        } else if (param.equals("saveAs")) {
            fileChooser = new JFileChooser();
            FileFilter txtFilter = new FileNameExtensionFilter("txt files", "txt");
            fileChooser.setFileFilter(txtFilter);
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) ;
            {
                file = fileChooser.getSelectedFile();
                filePath = file.getPath();
            }
        }
        if (haveFilePath == false && checkFileIsOpen(filePath)) {
            JOptionPane.showMessageDialog(null, "This filename isn't free !!!");
        } else {
            try {
                PrintWriter printWriter = new PrintWriter(file);
                Scanner scanner = new Scanner(application.getTable().get(application.getSelectedIndex()).getTextArea().getText());
                while (scanner.hasNext()) {
                    printWriter.println(scanner.nextLine());
                }
                scanner.close();
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            application.getjTabbedPane().setTitleAt(application.getSelectedIndex(), file.getName());
            application.getTable().get(application.getSelectedIndex()).setName(file.getName());
            application.getTable().get(application.getSelectedIndex()).setFilePath(file.getPath());
        }
    }

    /**
     * Method closees an active text card
     */
    public void close() {
        String filePath = application.getTable().get(application.getSelectedIndex()).getFilePath();
        if (filePath == null) {
            int option = JOptionPane.showConfirmDialog(null, "Do you want save document !!!", "Document is not saved", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                saveFile("saveAs");
                String fileName = application.getTable().get(application.getSelectedIndex()).getName();
                JOptionPane.showMessageDialog(null, "File " + fileName + " was saved");
            }
        }else{
            saveFile("saveFile");
        }
         if (application.getTable().size() == 1){
            int option = JOptionPane.showConfirmDialog(null, "Do you want close last document !!!", "This is last document", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                System.exit(0);
            }
        }
        application.getjTabbedPane().remove(application.getSelectedIndex());
        application.getTable().remove(application.getSelectedIndex());
    }

    /**
     * Method closes all text cards
     */
    public void closeAll() {
        int tabSize = application.getTable().size();
        for (int i = tabSize; i > 1; i--) {
            close();
        }
    }

    /**
     * Method checks whether the selected file is already open
     *
     * @param filePath filepath selected file
     * @return true if file is open  and false if file not open
     */
    public boolean checkFileIsOpen(String filePath) {
        ArrayList<String> fileList = new ArrayList<>();
        for (Tab file : application.getTable()) {
            fileList.add(file.getFilePath());
        }
        if (fileList.contains(filePath)) {
            return true;
        } else {
            return false;
        }
    }
}
