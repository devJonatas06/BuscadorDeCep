package BUSCARCEP.cep;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.net.URI;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sobre extends JDialog {

    private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Sobre dialog = new Sobre();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the dialog.
     */
    public Sobre() {
        setModal(true);
        setResizable(false);
        setForeground(Color.WHITE);
        getContentPane().setForeground(Color.WHITE);
        getContentPane().setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        setTitle("sobre");
        setIconImage(Toolkit.getDefaultToolkit().getImage("/home/jonatasdev/Downloads/353416_home_icon.png"));
        setBounds(150, 150, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Buscador de Cep 1.0");
        lblNewLabel.setBounds(12, 44, 373, 17);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("@Author Jonatas Freitas");
        lblNewLabel_1.setBounds(12, 84, 373, 17);
        getContentPane().add(lblNewLabel_1);

        JLabel lblWebService = new JLabel("WEB Service:");
        lblWebService.setBounds(34, 125, 169, 17);
        getContentPane().add(lblWebService);

        JLabel lblWebSerivce = new JLabel("republicavirtual.com.br");
        lblWebSerivce.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                link("https://www.republicavirtual.com.br/");

            }
        });
        lblWebSerivce.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblWebSerivce.setForeground(Color.BLUE);
        lblWebSerivce.setBounds(179, 125, 259, 17);
        getContentPane().add(lblWebSerivce);

        JButton github = new JButton("");
        github.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                link("https://github.com/devJonatas06");
            }
        });
        github.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        github.setBackground(Color.WHITE);
        github.setIcon(new ImageIcon("/home/jonatasdev/Downloads/394189_code_github_repository_icon.png"));
        github.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        github.setBorder(null);
        github.setBounds(44, 154, 48, 68);
        getContentPane().add(github);

        JLabel lblCliqueNoMeu = new JLabel("Clique no meu GitHub Para mais informacoes\n");
        lblCliqueNoMeu.setBounds(92, 173, 322, 17);
        getContentPane().add(lblCliqueNoMeu);

    }
    private void link(String site) {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(site);
            desktop.browse(uri);
        }catch(Exception e) {
            System.out.println();
        }

    }

}
