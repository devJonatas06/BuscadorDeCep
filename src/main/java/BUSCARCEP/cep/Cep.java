package BUSCARCEP.cep;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.Toolkit;

public class Cep extends JFrame {

    private static final long serialVersionUID = 1L; // Evita warnings de serialização
    private JPanel contentPane;
    private JTextField txtCep;
    private JTextField txtEndereco;
    private JTextField txtlocalidade;
    private JTextField txtBairro;
    private JComboBox<String> comboBox;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Cep frame = new Cep();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Cep() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("/home/jonatasdev/Downloads/353416_home_icon.png"));
        setTitle("Buscar Cep");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.control);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCep = new JLabel("CEP");
        lblCep.setBounds(22, 32, 31, 17);
        contentPane.add(lblCep);

        txtCep = new JTextField();
        txtCep.setBounds(71, 30, 143, 21);
        contentPane.add(txtCep);
        txtCep.setColumns(10);

        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setBounds(12, 71, 60, 17);
        contentPane.add(lblEndereco);

        txtEndereco = new JTextField();
        txtEndereco.setBounds(71, 69, 280, 21);
        txtEndereco.setEditable(false);
        contentPane.add(txtEndereco);
        txtEndereco.setColumns(10);

        JLabel lblBairro = new JLabel("Bairro");
        lblBairro.setBounds(22, 104, 60, 17);
        contentPane.add(lblBairro);

        JLabel lblLocalidade = new JLabel("Localidade");
        lblLocalidade.setBounds(12, 135, 105, 17);
        contentPane.add(lblLocalidade);

        txtlocalidade = new JTextField();
        txtlocalidade.setBounds(81, 133, 214, 21);
        txtlocalidade.setEditable(false);
        contentPane.add(txtlocalidade);
        txtlocalidade.setColumns(10);

        txtBairro = new JTextField();
        txtBairro.setBounds(71, 102, 280, 21);
        txtBairro.setEditable(false);
        contentPane.add(txtBairro);
        txtBairro.setColumns(10);

        JLabel lblUF = new JLabel("UF");
        lblUF.setBounds(307, 135, 60, 17);
        contentPane.add(lblUF);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(
                new String[]{"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
                        "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
        comboBox.setBounds(337, 130, 69, 26);
        comboBox.setEnabled(false);
        contentPane.add(comboBox);

        JButton btnCep = new JButton("Buscar");
        btnCep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cep = txtCep.getText().trim();
                if (!cep.matches("\\d{8}")) {
                    JOptionPane.showMessageDialog(null, "CEP inválido! Informe um CEP com 8 dígitos numéricos.");
                } else {


                    buscarCep(cep);
                }
            }
        });
        btnCep.setBounds(246, 27, 105, 27);
        contentPane.add(btnCep);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtCep.setText("");
                txtEndereco.setText("");
                txtBairro.setText("");
                txtlocalidade.setText("");
                comboBox.setSelectedIndex(0);
            }
        });
        btnLimpar.setBounds(12, 177, 105, 27);
        contentPane.add(btnLimpar);

        JButton btnSobre = new JButton("");
        btnSobre.setIcon(new ImageIcon("/home/jonatasdev/Downloads/4092564_profile_about_mobile ui_user_icon.png"));
        btnSobre.setBackground(SystemColor.control);
        btnSobre.setBorder(null);
        btnSobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Criar uma nova instância da classe Sobre
                Sobre sobreDialog = new Sobre();
                sobreDialog.setVisible(true);  // Tornar a janela visível
            }
        });

        btnSobre.setBounds(363, 32, 48, 48);
        contentPane.add(btnSobre);

        JLabel lblStatus = new JLabel("");
        lblStatus.setBounds(222, 32, 19, 20);
        contentPane.add(lblStatus);
    }

    private void buscarCep(String cep) {
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/xml/");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(url.openStream());

            Element root = document.getDocumentElement();

            String logradouro = getElementText(root, "logradouro");
            String bairro = getElementText(root, "bairro");
            String localidade = getElementText(root, "localidade");
            String uf = getElementText(root, "uf");

            txtEndereco.setText(logradouro != null ? logradouro : "Não encontrado");
            txtBairro.setText(bairro != null ? bairro : "Não encontrado");
            txtlocalidade.setText(localidade != null ? localidade : "Não encontrado");
            comboBox.setSelectedItem(uf != null ? uf : "Não encontrado");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar CEP. Verifique sua conexão com a internet.");
            e.printStackTrace();
        }
    }

    private String getElementText(Element root, String tagName) {
        try {
            return root.getElementsByTagName(tagName).item(0).getTextContent();
        } catch (Exception e) {
            return null;
        }
    }
}
