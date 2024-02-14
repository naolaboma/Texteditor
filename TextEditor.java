package wordEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.lang.*;
import java.util.Scanner;
public class TextEditor extends JFrame {
    private JTextField counter;

    private JTextArea textArea;
    private JLabel label1,label2,fontStylelabel,fontSizeLabel,click,words;
    private JScrollPane scroll;
    private JButton boldButton,italicsButton,colorButton,plainButton,romanButton,WordButton,pasteButton
            ,copyButton,cutButton;
    private JMenuBar menu;
    private JMenuItem open,save,saveAs,news,exit,defaultTheme,darkTheme,yellowTheme,greenTheme,silverTheme,orangeTheme,lavenderTheme,azureTheme,copy,paste,select,cut;
    private JMenu fileMenu,themeMenu,editMenu;
    private JComboBox fontBox;
    private JSpinner fontSize;

    private final String[] fontStyle =GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    final ImageIcon image= new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\text.png");

    public TextEditor() {
        setTitle("TextEditor");
        setSize(1500, 750);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(128, 216, 255));
        setIconImage(image.getImage());
        build();
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    public void build(){
        //Local Variable Declaration for build method
        JPanel ClipboardPanel,viewPanel,fontPanel,panel1,panel2;
        // Now we are creating Menu bar
        menu=new JMenuBar();
        menu.setPreferredSize(new Dimension(1400,50));
        //Now we are creating file menu and then its items
        fileMenu=new JMenu("File");
        // Create open item and set accelerator then add action listener
        open=new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        open.addActionListener(new Listener());
        // Create save item and set accelerator then add action listener
        save=new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        save.addActionListener(new Listener());
        // Create save as item and add action listener
        saveAs=new JMenuItem("Save As");
        saveAs.addActionListener(new Listener());
        // Create new item and set accelerator then add action listener
        news=new JMenuItem("New");
        news.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        news.addActionListener(new Listener());
        // Create exit item and set accelerator then add action listener
        exit=new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
        exit.addActionListener(new Listener());
        //Now we are adding edit menu items
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.add(news);
        fileMenu.add(exit);
        //Now we are creating theme menu and then its items
        themeMenu =new JMenu("Theme");
        // Create default item and set background color then add action listener
        defaultTheme=new JMenuItem("Default");
        defaultTheme.setBackground(Color.WHITE);
        defaultTheme.addActionListener(new Listener());
        // Create dark theme  item and set background color then add action listener
        darkTheme=new JMenuItem("Dark");
        darkTheme. setBackground(Color.DARK_GRAY);
        darkTheme.setForeground(Color.LIGHT_GRAY);
        darkTheme.addActionListener(new Listener());
        // Create yellow theme item and set background color then add action listener
        yellowTheme=new JMenuItem("Yellow");
        yellowTheme.setBackground(Color.YELLOW);
        yellowTheme.addActionListener(new Listener());
        // Create green theme item and set background color then add action listener
        greenTheme=new JMenuItem("Green");
        greenTheme.setBackground(Color.GREEN);
        greenTheme.addActionListener(new Listener());
        // Create silver theme item and set background color then add action listener
        silverTheme=new JMenuItem("Silver");
        silverTheme.setBackground(new Color(192,192,192));
        silverTheme.addActionListener(new Listener());
        // Create orange theme and set background color then add action listener
        orangeTheme=new JMenuItem("Orange");
        orangeTheme.addActionListener(new Listener());
        orangeTheme.setBackground(new Color(255,165,0));
        // Create lavender theme item and set background color then add action listener
        lavenderTheme=new JMenuItem("Lavender");
        lavenderTheme.addActionListener(new Listener());
        lavenderTheme.setBackground(new Color(230,230,250));
        // Create azure theme item and set background color then add action listener
        azureTheme=new JMenuItem("Azure");
        azureTheme.setBackground(new Color(240,255,255));
        azureTheme.addActionListener(new Listener());
        //Now we are adding theme menu items
        themeMenu.add(defaultTheme);
        themeMenu.add(darkTheme);
        themeMenu.add(yellowTheme);
        themeMenu.add(greenTheme);
        themeMenu.add(silverTheme);
        themeMenu.add(orangeTheme);
        themeMenu.add(lavenderTheme);
        themeMenu.add(azureTheme);
        //Now we are creating edit menu and then its items
        editMenu=new JMenu("Edit");
        // Create cut item and set accelerator then add action listener
        cut=new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        cut.addActionListener(new Listener());
        // Create copy item and set accelerator then add action listener
        copy=new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        copy.addActionListener(new Listener());
        // Create paste item and set accelerator then add action listener
        paste=new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        paste.addActionListener(new Listener());
        // Create select item and set accelerator then action listener
        select=new JMenuItem("Select");
        select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        select.addActionListener(new Listener());
        //Now we are adding edit menu items
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.add(select);
        //Now we are adding file,theme,and edit menus in the menu bar
        menu.add(fileMenu);
        menu.add(themeMenu);
        menu.add(editMenu);
        // create panel1 that contains Clipboard, view, and font panels;
        panel1 =new JPanel();
        panel1.setPreferredSize(new Dimension(1400,130));
        panel1.setLayout(new GridLayout(1,3,4,4));
        // create panel2 that contains text area;
        panel2=new JPanel();
        panel2.setPreferredSize(new Dimension(1400,500));
        // these objects are used for importing image icons from my computer file to decorate buttons
        Icon iconPaste = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\paste.png");
        Icon iconCopy = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\duplicate.png");
        Icon iconCut = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\cut.png");
        Icon iconCounter = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\counter.png");
        Icon iconbold = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\Bold.png");
        Icon iconitalics = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\italic (1).png");
        Icon iconcolor = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\color-palette.png");
        Icon iconplain = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\letter-p.png");
        Icon iconroman = new ImageIcon("C:\\Users\\User\\OneDrive\\Pictures\\letter-r.png");

        // create  clipboard buttons
        //create paste button and set background color then add action listener
        pasteButton=new JButton(iconPaste);
        pasteButton.setBackground(new Color(250,235,215));
        pasteButton.addActionListener(new Listener());
        pasteButton.setText("Paste");
        //create copy button and set background color then add action listener

        copyButton=new JButton(iconCopy);
        copyButton.setBackground(new Color(250,235,215));
        copyButton.addActionListener(new Listener());
        copyButton.setText("Copy");
        //create cut button and set background color then add action listener

        cutButton=new JButton(iconCut);
        cutButton.setBackground(new Color(250,235,215));
        cutButton.addActionListener(new Listener());
        cutButton.setText("Cut");

        //  create clipboard panel that contains past,copy,and cut buttons
        ClipboardPanel =new JPanel();
        ClipboardPanel.setLayout(new GridLayout(1,3,4,4));
        ClipboardPanel.setBackground(new Color(128, 216, 255));
        ClipboardPanel.setBorder(BorderFactory.createTitledBorder("Clipboard"));
        //add buttons on the clipboard panel
        ClipboardPanel.add(pasteButton);
        ClipboardPanel.add(copyButton);
        ClipboardPanel.add(cutButton);
        // create view panel that contains click label,word button, word panel, and counter text field
        viewPanel = new JPanel();
        viewPanel.setBackground(new Color(229,204,255));
        viewPanel.setLayout(new GridLayout(2,2,4,4));
        viewPanel.setBorder(BorderFactory.createTitledBorder("View"));
        // we are creating word button
        WordButton = new JButton(iconCounter);
        WordButton.addActionListener(new Listener());
        WordButton.setText("Word Counter:");
        // we are creating counter text field
        counter = new JTextField();
        // we are creating click label
        click = new JLabel("Click Here:");
        // we are creating word label
        words = new JLabel("Number of words are:");
        // now we add components in the view panel
        viewPanel.add(click);
        viewPanel.add(WordButton);
        viewPanel.add(words);
        viewPanel.add(counter);
        //create  combo box for font style and action listener
        fontBox = new JComboBox(fontStyle);
        fontBox.setSelectedItem("Arial");
        fontBox.addActionListener(new Listener());
        // create action font size spinner and add change in listener
        fontSize = new JSpinner();
        fontSize.setValue(12);
        fontSize.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int)fontSize.getValue()));
            }
        });
        //create font panel that labels and buttons
        fontPanel=new JPanel();
        fontPanel.setBackground(new Color(128, 216, 255));
        fontPanel.setBorder(BorderFactory.createTitledBorder("Font"));
        fontPanel.setLayout(new GridLayout(3,2,4,4));
        label1=new JLabel();
        fontStylelabel = new JLabel("    Font Style:");
        fontSizeLabel = new JLabel("    Font Size:");
        label2=new JLabel();
        label2.setLayout(new GridLayout(1,3,8,4));
        label1.setLayout(new GridLayout(1,3,8,4));

        boldButton=new JButton(iconbold);
        boldButton.addActionListener(new Listener());

        italicsButton=new JButton(iconitalics);
        italicsButton.addActionListener(new Listener());

        colorButton=new JButton(iconcolor);
        colorButton.addActionListener(new Listener());

        plainButton=new JButton(iconplain);
        plainButton.addActionListener(new Listener());

        romanButton=new JButton(iconroman);
        romanButton.addActionListener(new Listener());
        boldButton.setBackground(Color.CYAN);
        italicsButton.setBackground(Color.CYAN);
        colorButton.setBackground(Color.CYAN);
        plainButton.setBackground(Color.CYAN);
        romanButton.setBackground(Color.CYAN);
        label1.add(boldButton);
        label1.add(italicsButton);
        label2.add(colorButton);
        label2.add(plainButton);
        label2.add(romanButton);
        // add components in the fontPanel
        fontPanel.add(fontStylelabel);
        fontPanel.add(fontBox);
        fontPanel.add(fontSizeLabel);
        fontPanel.add(fontSize);
        fontPanel.add(label1);
        fontPanel.add(label2);
        // add clipboard,view,and font panel in the panel1
        panel1.add(ClipboardPanel);
        panel1.add(viewPanel);
        panel1.add(fontPanel);
        // we are creating text are
        textArea=new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Now we create the vertical Scroll bar for the text area
        scroll= new JScrollPane();
        scroll=new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(1300,480));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // add the scroll bar in the panel2
        panel2.add(scroll);
        add(menu);
        add(panel1);
        add(panel2);

    }

    // we create listener class to add functionalities for all components
    static class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == italicsButton) {
                textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.ITALIC, textArea.getFont().getSize()));
            }
            if (e.getSource() == boldButton) {
                textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.BOLD, textArea.getFont().getSize()));
            }
            if (e.getSource() == colorButton) {
                textArea.getSelectedText();
                JColorChooser colorChooser = new JColorChooser();
                Color colerChoosed = JColorChooser.showDialog(null, "Font Colors", Color.blue);
                textArea.setForeground(colerChoosed);
            }
            if (e.getSource() == plainButton) {
                textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
            }
            if (e.getSource() == romanButton) {
                textArea.getSelectedText();
                textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.CENTER_BASELINE,textArea.getFont().getSize()));
            }
            if (e.getSource() == fontBox) {
                textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
            }
            if (e.getSource() == cut||e.getSource()==cutButton) {
                textArea.cut();
            }
            if (e.getSource() == copy || e.getSource() == copyButton) {
                textArea.copy();
            }
            if (e.getSource() == paste || e.getSource() == pasteButton) {
                textArea.paste();
            }
            if (e.getSource() == select) {
               textArea.getSelectedText();
            }
            if (e.getSource() == WordButton){
                String txt=textArea.getText();
                try(Scanner sc = new Scanner(txt)){
                    int count=0;
                    while(sc.hasNext()){
                        sc.next();
                        count++;
                    }
                    counter.setText(" "+count);
                }}
            if (e.getSource() == save || e.getSource() == saveAs) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                int accepter = fileChooser.showSaveDialog(null);
                if (accepter == JFileChooser.APPROVE_OPTION) {
                    File file;
                    PrintWriter writer = null;
                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        writer = new PrintWriter(file);
                        writer.println(textArea.getText());
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                        System.out.println(e1.getMessage());
                    } finally {
                        writer.close();
                    }
                }
            }
            if (e.getSource() == open) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                int accepter = fileChooser.showOpenDialog(null);
                if (accepter == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    Scanner fileScanner = null;
                    if (file.isFile()) {
                        try {
                            fileScanner = new Scanner(file);
                            while (fileScanner.hasNext()) {
                                String line = fileScanner.nextLine();
                                textArea.append(line);
                            }
                        } catch (FileNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                            System.out.println(e1.getMessage());
                        } finally {
                            fileScanner.close();
                        }
                    }
                }
            }
            if (e.getSource() == news) {
                String stringAccepter = textArea.getText();
                if (!stringAccepter.equals("")) {
                    textArea.setText("");
                }
            }
            if (e.getSource() == exit) {
                int x = JOptionPane.showConfirmDialog(null, "Want to save your changes to Doc1?", "MyEditor", JOptionPane.YES_NO_CANCEL_OPTION);
                System.out.println(x);
                if (x == 0) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                    int accepter = fileChooser.showSaveDialog(null);
                    if (accepter == JFileChooser.APPROVE_OPTION) {
                        File file;
                        PrintWriter writer = null;
                        file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        try {
                            writer = new PrintWriter(file);
                            writer.println(textArea.getText());
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                            System.out.println(e1.getMessage());
                        } finally {
                            writer.close();
                        }
                    }
                } else if (x == 1) {
                    System.exit(0);
                } else if (x == -1) {

                }
             }

                if (e.getSource() == darkTheme) {
                    textArea.setBackground(Color.DARK_GRAY);
                    textArea.setForeground(Color.WHITE);
                    getContentPane().setBackground(new Color(7, 60, 94));
                    menu.setBackground(new Color(201, 228, 246));
                }
                if (e.getSource() == yellowTheme) {
                    textArea.setBackground(Color.YELLOW);
                    textArea.setForeground(Color.black);
                    getContentPane().setBackground(new Color(117, 152, 175, 144));
                    menu.setBackground(new Color(231, 213, 147));
                }
                if (e.getSource() == greenTheme) {
                    textArea.setBackground(Color.GREEN);
                    textArea.setForeground(Color.black);
                    getContentPane().setBackground(new Color(206, 186, 222, 189));
                    menu.setBackground(new Color(169, 203, 101));
                }
                if (e.getSource() == defaultTheme) {
                    textArea.setBackground(Color.WHITE);
                    textArea.setForeground(Color.black);
                    getContentPane().setBackground(new Color(128, 216, 255));
                    menu.setBackground(new Color(235, 243, 248));
                }
                if (e.getSource() == silverTheme) {
                    textArea.setBackground(new Color(192,192,192));
                    textArea.setForeground(Color.black);
                    getContentPane().setBackground(new Color(224, 148, 218));
                    menu.setBackground(new Color(123, 125, 222));
                }
                if (e.getSource() == orangeTheme) {
                    textArea.setBackground(new Color(255,165,0));
                    textArea.setForeground(Color.black);
                    getContentPane().setBackground(new Color(160, 245, 225));
                    menu.setBackground(new Color(190, 169, 171, 255));
                }
                if (e.getSource() == lavenderTheme) {
                    textArea.setBackground(new Color(230,230,250));
                    textArea.setForeground(Color.black);
                    getContentPane().setBackground(new Color(87, 98, 93));
                    menu.setBackground(new Color(66, 171, 241));
                }
                if (e.getSource() == azureTheme) {
                    textArea.setBackground(new Color(154, 144, 234));
                    textArea.setForeground(Color.black);
                }
            }
        }
    public static void main(String[] args) {
        new TextEditor();
    }
}