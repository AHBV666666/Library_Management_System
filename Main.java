package Library_Management_System;

public class Main {

    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();
        try {
            bookRepository.insertBook(new Book("Java Programming", "James Gosling", 550000, 10));
            bookRepository.insertBook(new Book("Clean Code", "Robert C. Martin", 700000, 5));
            bookRepository.insertBook(new Book("Head First Java", "Kathy Sierra", 650000, 7));
            bookRepository.insertBook(new Book("Effective Java", "Joshua Bloch", 800000, 4));
            bookRepository.insertBook(new Book("Design Patterns", "GoF", 900000, 3));

            memberRepository.addMember(new Member("Ali Ahmadi", "09123456789"));
            memberRepository.addMember(new Member("Sara Mohammadi", "09351234567"));
            memberRepository.addMember(new Member("Reza Karimi", "09901234567"));

            bookRepository.findBookById(1);

            memberRepository.findMemberById(1);

            bookRepository.updatePrice(1, 600000);

            bookRepository.deleteBook(5);

            memberRepository.deleteMember(3);

        } catch (BookNotFoundException | MemberNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}