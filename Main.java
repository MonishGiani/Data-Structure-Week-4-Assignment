    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.ArrayList;

    class Contact {
        private String name;
        private String phoneNumber;
        private String email;

        public Contact(String name, String phoneNumber, String email) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail: " + email;
        }
    }

    class ContactBook {
        private ArrayList<Contact> contacts;

        public ContactBook() {
            contacts = new ArrayList<>();
        }

        public void addContact(Contact contact) {
            contacts.add(contact);
        }

        public void deleteContact(String email) {
            contacts.removeIf(contact -> contact.getEmail().equalsIgnoreCase(email));
        }

        public Contact searchByEmail(String email) {
            for (Contact contact : contacts) {
                if (contact.getEmail().equalsIgnoreCase(email)) {
                    return contact;
                }
            }
            return null;
        }

        public ArrayList<Contact> getContacts() {
            return contacts;
        }
    }

    public class Main {
        private ContactBook contactBook;
        private JFrame frame;
        private JTextArea textArea;
        private JTextField emailField;

        public Main() {
            contactBook = new ContactBook();
            frame = new JFrame("Contact Book");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 1));

            JButton addButton = new JButton("Add Contact");
            JButton deleteButton = new JButton("Delete Contact");
            JButton searchButton = new JButton("Search by Email");
            JButton printButton = new JButton("Print Contacts");
            JButton quitButton = new JButton("Quit");

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = JOptionPane.showInputDialog(frame, "Enter name:");
                    String phoneNumber = JOptionPane.showInputDialog(frame, "Enter phone number:");
                    String email = JOptionPane.showInputDialog(frame, "Enter email:");
                    Contact newContact = new Contact(name, phoneNumber, email);
                    contactBook.addContact(newContact);
                    JOptionPane.showMessageDialog(frame, "Contact added successfully.");
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String email = JOptionPane.showInputDialog(frame, "Enter email of the contact to delete:");
                    contactBook.deleteContact(email);
                    JOptionPane.showMessageDialog(frame, "Contact deleted successfully.");
                }
            });

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String email = JOptionPane.showInputDialog(frame, "Enter email to search:");
                    Contact contact = contactBook.searchByEmail(email);
                    if (contact != null) {
                        JOptionPane.showMessageDialog(frame, contact.toString(), "Contact Found", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Contact not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            printButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Contact> contacts = contactBook.getContacts();
                    StringBuilder sb = new StringBuilder();
                    for (Contact contact : contacts) {
                        sb.append(contact.toString()).append("\n\n");
                    }
                    textArea.setText(sb.toString());
                }
            });

            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });

            panel.add(addButton);
            panel.add(deleteButton);
            panel.add(searchButton);
            panel.add(printButton);
            panel.add(quitButton);

            textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane);

            frame.add(panel);
            frame.setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Main();
                }
            });
        }
    }
