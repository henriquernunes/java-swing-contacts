package panels;

import javax.swing.*;
import java.awt.*;

import app.ContactsApp;
import contacts.Contact;
import design.RoundedButton;
import design.UITheme;

public class ContactDetailPanel extends JDialog {
  private Contact contact;
  private ContactsApp parentApp;

  public ContactDetailPanel(Frame parent, Contact contact, ContactsApp app) {
    super(parent, "Detalhes do Contato", true);
    this.contact = contact;
    this.parentApp = app;
    initComponents();
    setLocationRelativeTo(parent);
  }

  private void initComponents() {
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(UITheme.WHITE);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

    // Nome completo em grande
    JLabel nameLabel = new JLabel(contact.getFullName());
    nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
    nameLabel.setForeground(UITheme.TEXT_DARK);
    nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainPanel.add(nameLabel);

    mainPanel.add(Box.createVerticalStrut(20));

    // Telefone com ícone
    JPanel phonePanel = new JPanel();
    phonePanel.setBackground(UITheme.WHITE);
    phonePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
    phonePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

    JLabel phoneIconLabel = new JLabel("<>");
    phoneIconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    phoneIconLabel.setForeground(UITheme.PRIMARY_BLUE);

    JLabel phoneLabel = new JLabel(contact.getPhone());
    phoneLabel.setFont(UITheme.getTitleFont());
    phoneLabel.setForeground(UITheme.PRIMARY_BLUE);

    phonePanel.add(phoneIconLabel);
    phonePanel.add(phoneLabel);
    mainPanel.add(phonePanel);

    mainPanel.add(Box.createVerticalStrut(40));

    // Botões de ação (editar e deletar)
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(UITheme.WHITE);
    buttonsPanel.setLayout(new GridLayout(1, 2, 15, 10));
    buttonsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

    // Botão Editar
    RoundedButton editBtn = new RoundedButton("- Editar", 25, 25);
    editBtn.setBackground(UITheme.PRIMARY_BLUE);
    editBtn.setForeground(UITheme.WHITE);
    editBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
    editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    editBtn.addActionListener(e -> editContact());

    // Botão Deletar
    RoundedButton deleteBtn = new RoundedButton("x Deletar", 25, 25);
    deleteBtn.setBackground(UITheme.DELETE_RED);
    deleteBtn.setForeground(UITheme.WHITE);
    deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
    deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    deleteBtn.addActionListener(e -> deleteContact());

    buttonsPanel.add(editBtn);
    buttonsPanel.add(deleteBtn);
    mainPanel.add(buttonsPanel);

    getContentPane().add(mainPanel);
  }

  private void editContact() {
    dispose();
    ContactAddPanel dialog = new ContactAddPanel(parentApp, contact, parentApp);
    dialog.setVisible(true);
  }

  private void deleteContact() {
    int option = JOptionPane.showConfirmDialog(this,
        "Tem certeza que deseja deletar " + contact.getFullName() + "?",
        "Confirmar Exclusão",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);

    if (option == JOptionPane.YES_OPTION) {
      parentApp.deleteContact(contact);
      dispose();
      JOptionPane.showMessageDialog(parentApp, "Contato deletado com sucesso!",
          "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  public void setupLayout() {
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    setSize(350, 320);
    setResizable(false);
  }
}
