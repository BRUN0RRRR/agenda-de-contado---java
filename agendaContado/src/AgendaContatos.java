import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AgendaContatos extends JFrame {

    private final JTextField nomeField;
    private final JTextField telefoneField, emailField;
    private final JButton adicionarButton, editarButton, removerButton, limparButton;
    private final JList<String> listaContatos;
    private final DefaultListModel<String> modeloLista;
    private final ArrayList<Contato> contatos;

    public AgendaContatos() {
        setTitle("Agenda de Contatos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializando componentes
        contatos = new ArrayList<>();
        modeloLista = new DefaultListModel<>();
        listaContatos = new JList<>(modeloLista);
        nomeField = new JTextField(20);
        telefoneField = new JTextField(20);
        emailField = new JTextField(20);

        adicionarButton = new JButton("Adicionar");
        editarButton = new JButton("Editar");
        removerButton = new JButton("Remover");
        limparButton = new JButton("Limpar");

        // Configurando layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(telefoneField);
        formPanel.add(new JLabel("E-mail:"));
        formPanel.add(emailField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adicionarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(removerButton);
        buttonPanel.add(limparButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(listaContatos), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Adicionando eventos
        adicionarButton.addActionListener(e -> adicionarContato());
        editarButton.addActionListener(e -> editarContato());
        removerButton.addActionListener(e -> removerContato());
        limparButton.addActionListener(e -> limparCampos());

        setVisible(true);
    }

    private void adicionarContato() {
        String nome = nomeField.getText().trim();
        String telefone = telefoneField.getText().trim();
        String email = emailField.getText().trim();

        if (!nome.isEmpty() && !telefone.isEmpty() && !email.isEmpty()) {
            Contato contato = new Contato(nome, telefone, email);
            contatos.add(contato);
            modeloLista.addElement(contato.toString());
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
        }
    }

    private void editarContato() {
        int index = listaContatos.getSelectedIndex();
        if (index != -1) {
            String nome = nomeField.getText().trim();
            String telefone = telefoneField.getText().trim();
            String email = emailField.getText().trim();

            if (!nome.isEmpty() && !telefone.isEmpty() && !email.isEmpty()) {
                Contato contato = contatos.get(index);
                contato.setNome(nome);
                contato.setTelefone(telefone);
                contato.setEmail(email);
                modeloLista.set(index, contato.toString());
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato para editar.");
        }
    }

    private void removerContato() {
        int index = listaContatos.getSelectedIndex();
        if (index != -1) {
            contatos.remove(index);
            modeloLista.remove(index);
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato para remover.");
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        telefoneField.setText("");
        emailField.setText("");
        listaContatos.clearSelection();
    }

    // Classe interna para representar o contato
    private static class Contato {
        private String nome;
        private String telefone;
        private String email;

        public Contato(String nome, String telefone, String email) {
            this.nome = nome;
            this.telefone = telefone;
            this.email = email;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return nome + " - " + telefone + " - " + email;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AgendaContatos::new);
    }
}
