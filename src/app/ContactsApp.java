package app;

import javax.swing.*;

import contacts.AccessContacts;
import contacts.Contact;
import design.RoundedButton;
import design.RoundedPanel;
import design.UITheme;
import panels.ContactAddPanel;
import panels.ContactDetailPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ContactsApp extends JFrame {
  private AccessContacts accessContacts;
  private List<Contact> contacts;
  private JPanel mainContentPanel;
  private DefaultListModel<Contact> listModel;
  private JList<Contact> contactsList;
  private JLabel emptyStateLabel;

  public ContactsApp() {
    setTitle("Contatos");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(450, 800);
    setLocationRelativeTo(null);
    setResizable(false);

    accessContacts = new AccessContacts();
    contacts = accessContacts.list();

    createMenuBar();
    initComponents();
    setVisible(true);
  }

  private void createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBackground(UITheme.WHITE);

    // Menu Arquivo
    JMenu fileMenu = new JMenu("Arquivo");
    fileMenu.setFont(UITheme.getRegularFont());
    fileMenu.setForeground(UITheme.TEXT_DARK);

    JMenuItem newContactItem = new JMenuItem("Novo Contato");
    newContactItem.setFont(UITheme.getRegularFont());
    newContactItem.addActionListener(e -> addNewContact());
    fileMenu.add(newContactItem);

    fileMenu.addSeparator();

    JMenuItem exitItem = new JMenuItem("Sair");
    exitItem.setFont(UITheme.getRegularFont());
    exitItem.addActionListener(e -> System.exit(0));
    fileMenu.add(exitItem);

    // Menu Editar
    JMenu editMenu = new JMenu("Editar");
    editMenu.setFont(UITheme.getRegularFont());
    editMenu.setForeground(UITheme.TEXT_DARK);

    JMenuItem clearAllItem = new JMenuItem("Limpar Todos");
    clearAllItem.setFont(UITheme.getRegularFont());
    clearAllItem.addActionListener(e -> clearAllContacts());
    editMenu.add(clearAllItem);

    JMenuItem searchItem = new JMenuItem("Buscar Contato");
    searchItem.setFont(UITheme.getRegularFont());
    searchItem.addActionListener(e -> searchContact());
    editMenu.add(searchItem);

    // Menu Ajuda
    JMenu helpMenu = new JMenu("Ajuda");
    helpMenu.setFont(UITheme.getRegularFont());
    helpMenu.setForeground(UITheme.TEXT_DARK);

    JMenuItem aboutItem = new JMenuItem("Sobre");
    aboutItem.setFont(UITheme.getRegularFont());
    aboutItem.addActionListener(e -> showAbout());
    helpMenu.add(aboutItem);

    // Adicionar menus à barra
    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(helpMenu);

    setJMenuBar(menuBar);
  }

  private void clearAllContacts() {
    int option = JOptionPane.showConfirmDialog(this,
        "Tem certeza que deseja limpar todos os contatos?",
        "Confirmar Limpeza",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);

    if (option == JOptionPane.YES_OPTION) {
      contacts.clear();
      listModel.clear();
      accessContacts.saveContacts(contacts);
      emptyStateLabel.setVisible(true);
      JOptionPane.showMessageDialog(this, "Todos os contatos foram removidos!",
          "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private void searchContact() {
    String searchName = JOptionPane.showInputDialog(this,
        "Digite o nome para buscar:",
        "Buscar Contato",
        JOptionPane.QUESTION_MESSAGE);

    if (searchName != null && !searchName.trim().isEmpty()) {
      List<Contact> results = accessContacts.searchName(searchName);
      if (results.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Nenhum contato encontrado com: " + searchName,
            "Busca", JOptionPane.INFORMATION_MESSAGE);
      } else if (results.size() == 1) {
        // Se encontrar apenas um contato, abre direto
        showContactDetail(results.get(0));
      } else {
        // Se encontrar vários, mostra uma lista para o usuário escolher
        Contact selectedContact = (Contact) JOptionPane.showInputDialog(this,
            "Selecione um contato:",
            "Resultados da Busca",
            JOptionPane.QUESTION_MESSAGE,
            null,
            results.toArray(),
            results.get(0));

        if (selectedContact != null) {
          showContactDetail(selectedContact);
        }
      }
    }
  }

  private void showAbout() {
    JOptionPane.showMessageDialog(this,
        "Aplicação de Gerenciamento de Contatos\n\n" +
            "POO - 25/2\n" +
            "Desenvolvido com Swing\n\n" +
            "Henrique Nunes - João Scarinci.",
        "Sobre", JOptionPane.INFORMATION_MESSAGE);
  }

  private void initComponents() {
    // Painel principal com cor de fundo
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(UITheme.DARK_BACKGROUND);
    mainPanel.setLayout(new BorderLayout());

    // Header
    JPanel headerPanel = createHeaderPanel();
    mainPanel.add(headerPanel, BorderLayout.NORTH);

    // Conteúdo principal
    mainContentPanel = new JPanel();
    mainContentPanel.setBackground(UITheme.DARK_BACKGROUND);
    mainContentPanel.setLayout(new BorderLayout());

    // Lista de contatos
    listModel = new DefaultListModel<>();
    for (Contact c : contacts) {
      listModel.addElement(c);
    }

    contactsList = new JList<>(listModel);
    contactsList.setCellRenderer(new ContactCellRenderer());
    contactsList.setBackground(UITheme.DARK_BACKGROUND);
    contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    contactsList.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int index = contactsList.locationToIndex(e.getPoint());
        if (index >= 0) {
          Contact selectedContact = contactsList.getModel().getElementAt(index);
          contactsList.setSelectedIndex(index);

          Timer timer = new Timer(25, event -> {
            contactsList.clearSelection();
            showContactDetail(selectedContact);
          });
          timer.setRepeats(false);
          timer.start();
        }
      }
    });

    JScrollPane scrollPane = new JScrollPane(contactsList);
    scrollPane.setBackground(UITheme.DARK_BACKGROUND);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    scrollPane.getViewport().setBackground(UITheme.DARK_BACKGROUND);

    // Empty state label
    emptyStateLabel = new JLabel("Nenhum contato");
    emptyStateLabel.setFont(UITheme.getSubtitleFont());
    emptyStateLabel.setForeground(UITheme.TEXT_LIGHT);
    emptyStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    emptyStateLabel.setVisible(contacts.isEmpty());

    mainContentPanel.add(scrollPane, BorderLayout.CENTER);
    if (contacts.isEmpty()) {
      mainContentPanel.add(emptyStateLabel, BorderLayout.CENTER);
    }

    mainPanel.add(mainContentPanel, BorderLayout.CENTER);

    getContentPane().add(mainPanel);
  }

  private JPanel createHeaderPanel() {
    JPanel headerPanel = new JPanel();
    headerPanel.setBackground(UITheme.WHITE);
    headerPanel.setLayout(new BorderLayout());
    headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    // Painel central com título centralizado
    JPanel centerPanel = new JPanel();
    centerPanel.setBackground(UITheme.WHITE);
    centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

    // Título
    JLabel titleLabel = new JLabel("Contatos");
    titleLabel.setFont(UITheme.getHeaderFont());
    titleLabel.setForeground(UITheme.TEXT_DARK);

    centerPanel.add(titleLabel);
    headerPanel.add(centerPanel, BorderLayout.CENTER);

    // Botão Adicionar
    RoundedButton addButton = new RoundedButton("+", 20, 20);
    addButton.setBackground(UITheme.PRIMARY_BLUE);
    addButton.setForeground(UITheme.WHITE);
    addButton.setPreferredSize(new Dimension(45, 45));
    addButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    addButton.addActionListener(e -> addNewContact());

    headerPanel.add(addButton, BorderLayout.EAST);

    return headerPanel;
  }

  private void addNewContact() {
    ContactAddPanel dialog = new ContactAddPanel(this, null, this);
    dialog.setVisible(true);
  }

  private void showContactDetail(Contact contact) {
    ContactDetailPanel dialog = new ContactDetailPanel(this, contact, this);
    dialog.setupLayout();
    dialog.setVisible(true);
  }

  public void addContact(Contact contact) {
    contacts.add(contact);
    listModel.addElement(contact);
    accessContacts.saveContacts(contacts);
    emptyStateLabel.setVisible(false);
    JOptionPane.showMessageDialog(this, "Contato adicionado com sucesso!",
        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
  }

  public void updateContact(Contact contact) {
    accessContacts.saveContacts(contacts);
    contactsList.repaint();
    JOptionPane.showMessageDialog(this, "Contato atualizado com sucesso!",
        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
  }

  public void deleteContact(Contact contact) {
    int index = listModel.indexOf(contact);
    if (index >= 0) {
      listModel.removeElementAt(index);
      contacts.remove(contact);
      accessContacts.saveContacts(contacts);
      emptyStateLabel.setVisible(contacts.isEmpty());
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new ContactsApp());
  }

  // Custom Cell Renderer para exibir contatos de forma estilizada
  private class ContactCellRenderer extends JPanel implements ListCellRenderer<Contact> {
    private RoundedPanel roundedPanel;
    private JLabel nameLabel;

    public ContactCellRenderer() {
      setLayout(new BorderLayout(0, 0));
      setOpaque(true);
      setBackground(UITheme.DARK_BACKGROUND);
      setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));

      // Painel com bordas arredondadas
      roundedPanel = new RoundedPanel(20, 20, UITheme.LIGHT_GRAY);
      roundedPanel.setLayout(new BorderLayout());
      roundedPanel.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

      nameLabel = new JLabel();
      nameLabel.setFont(UITheme.getTitleFont());
      nameLabel.setOpaque(false);

      roundedPanel.add(nameLabel, BorderLayout.CENTER);
      add(roundedPanel, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Contact> list, Contact value,
        int index, boolean isSelected, boolean cellHasFocus) {
      nameLabel.setText(value.getFullName());

      if (isSelected) {
        roundedPanel.setBackgroundColor(UITheme.PRIMARY_BLUE);
        nameLabel.setForeground(UITheme.LIGHT_BLUE);
      } else {
        roundedPanel.setBackgroundColor(UITheme.LIGHT_GRAY);
        nameLabel.setForeground(UITheme.TEXT_DARK);
      }

      return this;
    }
  }
}
