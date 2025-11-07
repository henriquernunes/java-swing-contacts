# 📱 Agenda de Contatos - Java Swing

Uma aplicação moderna de agenda de contatos desenvolvida em Java Swing, com interface inspirada no iOS.

## 🎨 Características

✨ **Interface Moderna e Intuitiva**
- Design limpo e profissional inspirado no iOS
- Cores elegantes e harmoniosas
- Componentes responsivos e botões interativos

📋 **Funcionalidades Completas**
- ✅ Listar todos os contatos
- ✅ Adicionar novos contatos
- ✅ Editar contatos existentes
- ✅ Deletar contatos
- ✅ Persistência de dados em arquivo de texto

## 🎯 Experiência do Usuário
- Clique simples em um contato para ver detalhes
- Feedback visual ao clicar (destaque temporário por 500ms)
- Formulário validado para adicionar/editar contatos
- Confirmação de exclusão para evitar acidentes
- Renderização customizada de itens da lista com bordas arredondadas
- Interface responsiva e intuitiva inspirada em iOS

## 🚀 Como Compilar e Executar

### Pré-requisitos
- Java Development Kit (JDK) 8 ou superior instalado

### Compilação

No terminal, na raiz do projeto:

```bash
javac -d bin -cp src src/Main.java src/app/*.java src/contacts/*.java src/design/*.java src/panels/*.java
```

Ou simplesmente (se estiver na pasta src):

```bash
javac -d ../bin *.java app/*.java contacts/*.java design/*.java panels/*.java
```

### Execução

```bash
java -cp bin Main
```

Ou no diretório `bin`:

```bash
java Main
```

## 📁 Estrutura do Projeto

```
java-swing-contacts/
├── src/
│   ├── Main.java                      # Ponto de entrada da aplicação
│   ├── app/
│   │   └── ContactsApp.java           # Janela principal da aplicação
│   ├── contacts/
│   │   ├── Contact.java               # Modelo de dados do contato
│   │   └── AccessContacts.java        # Gerenciador de persistência
│   ├── panels/
│   │   ├── ContactDetailPanel.java    # Diálogo para exibir detalhes
│   │   └── ContactAddPanel.java       # Diálogo para adicionar/editar
│   └── design/
│       ├── UITheme.java               # Tema e configurações de UI
│       ├── RoundedButton.java         # Botão com bordas arredondadas
│       ├── RoundedPanel.java          # Painel com bordas arredondadas
│       └── RoundedBorder.java         # Classe auxiliar para bordas
├── bin/                               # Arquivos compilados
├── list.txt                           # Arquivo de armazenamento de contatos
└── README.md                          # Este arquivo
```

## 💾 Armazenamento de Dados

Os contatos são salvos em `list.txt` no formato CSV:

```
FirstName;LastName;PhoneNumber
Ana;Silva;987654321
Bruno;Almeida;912345678
```

## 🎨 Paleta de Cores

- **Azul Primário**: #007AFF (Botões principais)
- **Fundo**: #F2F2F7 (Cinza claro)
- **Vermelho**: #FF3B30 (Botão deletar)
- **Verde**: #34C759 (Estados positivos)
- **Laranja**: #FF9500 (Alertas)

## 📱 Classes Principais

### app/ContactsApp.java
A janela principal da aplicação. Gerencia:
- Exibição da lista de contatos com renderização customizada
- Feedback visual ao clicar em contatos (destaque temporário de 500ms)
- Abertura de diálogos de detalhes e edição
- Gerenciamento de adicionar, editar e deletar contatos

### contacts/Contact.java
Modelo de dados que representa um contato com:
- Nome (primeiro e último)
- Telefone
- Métodos para obter nome completo
- Getters e setters

### contacts/AccessContacts.java
Gerencia persistência de dados:
- Lê contatos do arquivo `list.txt`
- Salva contatos no arquivo
- Suporta operações CRUD
- Separador de campos: `;`

### panels/ContactDetailPanel.java
Diálogo para exibir detalhes de um contato:
- Mostra nome, telefone e ícones
- Botões para editar ou deletar o contato
- Confirmação antes de deletar

### panels/ContactAddPanel.java
Diálogo reutilizável para:
- Adicionar novos contatos
- Editar contatos existentes
- Validação de campos (primeiro nome, último nome, telefone)
- Cancelar ou salvar operação

### design/UITheme.java
Centraliza configurações visuais:
- Cores iOS-inspired (azul primário, cinza, vermelho, verde, laranja)
- Fontes padrão (Segoe UI em diferentes tamanhos)
- Constantes de estilo reutilizáveis

### design/RoundedButton.java
Componente customizado para botões com:
- Bordas arredondadas
- Efeitos visuais
- Suporte a cores personalizadas

### design/RoundedPanel.java
Componente customizado para painéis com:
- Bordas arredondadas
- Background colorido
- Bordas decorativas opcionais

### design/RoundedBorder.java
Classe auxiliar para renderizar bordas arredondadas

## 🔧 Personalizações

### Mudar as Cores

Abra `UITheme.java` e modifique as constantes de cor:

```java
public static final Color PRIMARY_BLUE = new Color(0, 122, 255);
```

### Mudar Tamanho da Janela

Em `ContactsApp.java`, modifique o construtor:

```java
setSize(450, 800); // Largura x Altura
```

## 📋 Formato de Dados

### list.txt

Cada linha representa um contato:
```
Primeiro Nome;Último Nome;Telefone
```

Exemplo:
```
Henrique;Nunes;987123456
Maria;Silva;982345678
```

## 🐛 Resolução de Problemas

### A aplicação não compila
- Verifique se o JDK está instalado: `javac -version`
- Certifique-se de que está na pasta correta do projeto

### Os contatos não salvam
- Verifique se `list.txt` existe na raiz do projeto
- Verifique permissões de leitura/escrita do arquivo

### Interface não renderiza corretamente
- Tente usar a fonte "Segoe UI" (padrão Windows)
- Em Linux/Mac, a fonte será substituída automaticamente

## 🎓 Conceitos Aprendidos

Este projeto demonstra:
- ✓ Uso de Swing para interfaces gráficas
- ✓ Layouts (BorderLayout, BoxLayout, GridLayout)
- ✓ Custom Renderers para listas
- ✓ Persistência de dados
- ✓ Validação de formulários
- ✓ Tratamento de eventos
- ✓ Diálogos modais
- ✓ Programação Orientada a Objetos

---

**Divirta-se criando e gerenciando seus contatos!** 📱✨
