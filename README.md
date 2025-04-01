# Buscador de CEP 1.0

Este é um projeto Java que busca informações sobre um endereço com base no CEP fornecido pelo usuário. A aplicação usa a API pública ViaCEP para obter os dados do endereço (como logradouro, bairro, cidade, estado) e exibi-los em uma interface gráfica simples feita com Swing.
Tecnologias Utilizadas

    Java 8 ou superior

    Swing para interface gráfica

    ViaCEP API para consultar o CEP

    DocumentBuilder para processar XML

Estrutura do Projeto

O projeto é composto por duas classes principais:

    Cep.java: A classe principal que contém a interface gráfica e a lógica para buscar o CEP.

    Sobre.java: Um diálogo que exibe informações sobre o projeto e o autor.

Classe Cep.java

A classe Cep.java é responsável pela interface gráfica e pela interação com a API ViaCEP. Ela possui campos para o usuário inserir o CEP e visualizar os dados do endereço.
Trecho de Código - Interface Gráfica

JLabel lblCep = new JLabel("CEP");
lblCep.setBounds(22, 32, 31, 17);
contentPane.add(lblCep);

txtCep = new JTextField();
txtCep.setBounds(71, 30, 143, 21);
contentPane.add(txtCep);
txtCep.setColumns(10);

Neste trecho, estamos criando um campo de texto para que o usuário insira o CEP. A interface usa o layout absoluto (setBounds) para posicionar os componentes na tela.
Trecho de Código - Buscar CEP

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

Este método realiza a consulta ao serviço ViaCEP utilizando a URL da API. Ele usa o DocumentBuilder para analisar o XML retornado e extrair informações como o logradouro, bairro, localidade e UF. Esses dados são então exibidos nos campos de texto da interface gráfica.
Classe Sobre.java

A classe Sobre.java é um diálogo simples que exibe informações sobre o projeto e o autor. Ela também inclui links clicáveis para o GitHub do autor e o site do serviço web utilizado.
Trecho de Código - Janela Sobre

JLabel lblNewLabel = new JLabel("Buscador de Cep 1.0");
lblNewLabel.setBounds(12, 44, 373, 17);
getContentPane().add(lblNewLabel);

JLabel lblWebSerivce = new JLabel("republicavirtual.com.br");
lblWebSerivce.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
lblWebSerivce.setForeground(Color.BLUE);
lblWebSerivce.setBounds(179, 125, 259, 17);
getContentPane().add(lblWebSerivce);

Este trecho configura os componentes da interface de "Sobre", exibindo o nome da versão do software e o link para o serviço web Republica Virtual. O link é clicável e abre o site no navegador do usuário.
Trecho de Código - Link GitHub

JButton github = new JButton("");
github.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
link("https://github.com/devJonatas06");
}
});
github.setIcon(new ImageIcon("/home/jonatasdev/Downloads/394189_code_github_repository_icon.png"));
github.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
github.setBorder(null);
github.setBounds(44, 154, 48, 68);
getContentPane().add(github);

Esse botão exibe o ícone do GitHub e, ao ser clicado, direciona o usuário para o repositório do autor.
Como Executar o Projeto

    Pré-requisitos: Certifique-se de ter o Java 8 ou superior instalado.

    Você pode verificar a versão do Java instalada com o comando:

java -version

Baixar o Projeto:

Clone o repositório ou baixe os arquivos diretamente:

git clone https://github.com/devJonatas06/BuscadorDeCep.git

Compilar e Executar:

Navegue até a pasta do projeto e execute o seguinte comando para compilar e rodar o projeto:

    javac -d . Cep.java Sobre.java
    java BUSCARCEP.cep.Cep

    Usando o Programa:

    Ao abrir a aplicação, insira um CEP válido (apenas números) no campo "CEP" e clique em Buscar para visualizar as informações de endereço. Se desejar limpar os campos, clique em Limpar.

Considerações Finais

Este projeto oferece uma solução simples para buscar informações de endereço a partir de um CEP. O uso da API ViaCEP facilita o acesso aos dados de forma rápida e sem a necessidade de chave de autenticação.

Se precisar de mais informações ou tiver dúvidas sobre o projeto, sinta-se à vontade para me contatar através do meu GitHub: GitHub - devJonatas06.
