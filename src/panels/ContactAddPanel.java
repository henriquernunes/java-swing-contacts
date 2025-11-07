package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import app.ContactsApp;
import contacts.Contact;
import design.RoundedButton;
import design.UITheme;

public class ContactAddPanel extends JDialog {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private Contact contact;
    private boolean saved = false;
    private ContactsApp parentApp;

    public ContactAddPanel(Frame parent, Contact contact, ContactsApp app) {
        super(parent, contact == null ? "Novo Contato" : "Editar Contato", true);
        this.contact = contact;
        this.parentApp = app;
        initComponents();
        setupLayout();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(UITheme.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Título
        JLabel titleLabel = new JLabel(contact == null ? "Adicionar Novo Contato" : "Editar Contato");
        titleLabel.setFont(UITheme.getTitleFont());
        titleLabel.setForeground(UITheme.TEXT_DARK);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(titleLabel);
        // mainPanel.add(Box.createVerticalStrut(-5));

        // Primeiro Nome
        JLabel firstNameLabel = new JLabel("Primeiro Nome");
        firstNameLabel.setFont(UITheme.getRegularFont());
        firstNameLabel.setForeground(UITheme.TEXT_DARK);
        firstNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(firstNameLabel);

        firstNameField = createStyledTextField();
        mainPanel.add(firstNameField);
        mainPanel.add(Box.createVerticalStrut(12));

        // Último Nome
        JLabel lastNameLabel = new JLabel("Último Nome");
        lastNameLabel.setFont(UITheme.getRegularFont());
        lastNameLabel.setForeground(UITheme.TEXT_DARK);
        lastNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lastNameLabel);

        lastNameField = createStyledTextField();
        mainPanel.add(lastNameField);
        mainPanel.add(Box.createVerticalStrut(12));

        // Telefone
        JLabel phoneLabel = new JLabel("Telefone");
        phoneLabel.setFont(UITheme.getRegularFont());
        phoneLabel.setForeground(UITheme.TEXT_DARK);
        phoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(phoneLabel);

        phoneField = createStyledTextField();
        mainPanel.add(phoneField);
        mainPanel.add(Box.createVerticalStrut(20));

        // Se está editando, preenchê os campos
        if (contact != null) {
            String[] parts = contact.getFullName().split(" ", 2);
            firstNameField.setText(parts[0]);
            if (parts.length > 1) {
                lastNameField.setText(parts[1]);
            }
            phoneField.setText(contact.getPhone());
        }

        // Botões
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(UITheme.WHITE);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        RoundedButton cancelButton = new RoundedButton("Cancelar", 15, 15);
        cancelButton.setBackground(UITheme.LIGHT_GRAY);
        cancelButton.setForeground(UITheme.TEXT_DARK);
        cancelButton.setPreferredSize(new Dimension(170, 40));
        cancelButton.setMaximumSize(new Dimension(170, 40));
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> dispose());

        RoundedButton saveButton = new RoundedButton("Salvar", 15, 15);
        saveButton.setBackground(UITheme.SUCCESS_GREEN);
        saveButton.setForeground(UITheme.WHITE);
        saveButton.setPreferredSize(new Dimension(170, 40));
        saveButton.setMaximumSize(new Dimension(170, 40));
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(this::saveContact);

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(Box.createHorizontalStrut(10));
        buttonsPanel.add(saveButton);

        mainPanel.add(buttonsPanel);

        getContentPane().add(mainPanel);
    }

    private void setupLayout() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(400, 320);
        setResizable(false);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(UITheme.getRegularFont());
        textField.setForeground(UITheme.TEXT_DARK);
        textField.setBackground(UITheme.LIGHT_BLUE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UITheme.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        textField.setPreferredSize(new Dimension(360, 40));
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return textField;
    }

    private void saveContact(ActionEvent e) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phone = phoneField.getText().trim();

        // Validações
        if (firstName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite o primeiro nome.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (lastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite o último nome.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um telefone.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar formato do telefone
        if (!isValidPhone(phone)) {
            JOptionPane.showMessageDialog(this, "Telefone inválido. Use apenas números.",
                    "Telefone Inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (contact == null) {
            // Novo contato
            Contact newContact = new Contact(firstName, lastName, phone);
            parentApp.addContact(newContact);
        } else {
            // Editar contato existente
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setPhone(phone);
            parentApp.updateContact(contact);
        }

        saved = true;
        dispose();
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d+");
    }

    public boolean isSaved() {
        return saved;
    }
}
