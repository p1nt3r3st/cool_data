public class Main {     // класс Main
    public static void main(String[] args) {
        // создаём объект "библиотека"
        Library library = new Library("Moscow library name Gagarin", new Address("Vernadsky", 86),
                "Moscow", new Director("Elena", "Shorohova", "Vitalevna"));

        // создаём объект "читательский зал"
        ReadingRoom readingRoom = new ReadingRoom("First", new Address("Nicolskay", 15), "Krasnodar",
                new Director("Tigran", "Djavadyan"), 526, 6, 627);

        // выводим эти объекты
        System.out.println(library);
        System.out.println(readingRoom);

        // создаём объект "читатель"
        Reader eugene = new Reader("MTUCI Library", new Address("Red street", 1), "Tomsk",
                new Director("Alexandr", "Antonov", "Sergeevich"), new Director("Eugene",
                "Bakharev", "Vladimirovich"), new Address("Pushkin's street", 38), 19, "M");
        // выводим читателя
        System.out.println(eugene);

        // новый "читатель"
        Reader Egor = library.createReader(new Director("Egor", "Zaycev"), new Address("Grey street",
                78), 20, "M");
        System.out.println(Egor);

        // создаём объект "выдача литературы" методом "createGetLiterature" из класса "библиотека"
        GetLiterature moscow = library.createGetLiterature(readingRoom, eugene, "Pushkin",
                "12.12.2023", "5", 500);
        // выводим "читательский зал"
        System.out.println(moscow);
    }
}

class Address{      // класс Адрес. сдержит улицу и номер дома
    protected String street;
    protected int house;

    Address(String street, int house){      // конструктор
        this.street = street;
        this.house = house;
    }

    // методы, возвращающие улицу и дом
    public String getStreet(){return  this.street;}

    public int getHouse() {
        return house;
    }

    // метод toString. Переводим данные объекта в строку
    public String toString(){
        return "st. " + this.street + ", h. " + this.house;
    }
}

class Director{     // класс Директор. Содержит имя, фамилию и, по желанию, отчество
    protected String name;
    protected String surname;
    protected String patronymic;

    Director(String name, String surname){      // конструктор без отчества
        this.name = name;
        this.surname = surname;
    }

    Director(String name, String surname, String patronymic){       // конструктор с отчеством
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String toString(){
        if (this.patronymic == null){
            return this.name + " " + this.surname;
        }
        else return this.name + " " + this.patronymic + " " + this.surname;
    }
}

class Library{          // класс библиотека
    protected String name;      // название библиотеки
    protected Address address;  // её адрес
    protected String city;      // город
    protected Director director;    // директор

    Library(String name, Address address, String city, Director director){      // конструктор класса
        this.name = name;
        this.address = address;
        this.city = city;
        this.director = director;
    }

    Library(){}     // можно создать пустой класс

    public String getName(){return this.name;}

    public Address getAddress() {
        return address;
    }

    public Director getDirector() {
        return director;
    }

    public String getCity() {
        return city;
    }

    public ReadingRoom createReadingRoom(int countLiterature, int floor, int room){
        return new ReadingRoom(this.name, this.address, this.city, this.director, countLiterature, floor, room);
    }

    public Reader createReader(Director readerName, Address addressWorking, int age, String gender){
        return new Reader(this.name, this.address, this.city, this.director, readerName, addressWorking, age, gender);
    }

    public GetLiterature createGetLiterature(ReadingRoom readingRoom, Reader reader, String nameLiterature,
                                             String dateOfGetting, String timeOfGetting, int sum){
        return new GetLiterature(this.name, this.address, this.city, this.director, readingRoom, reader, nameLiterature,
                dateOfGetting, timeOfGetting, sum);
    }

    public String toString(){
        return "{\nname: " + this.name +
                "\naddress: " + this.address +
                "\ncity: " + this.city +
                "\ndirector: " + this.director + "\n}";
    }
}



class ReadingRoom extends Library{      // класс "Читательский зал", наследуется от класса "Библиотека". имееет те же методы, что и родительский
    private int countLiterature;    // кол-во литературы
    private int floor;              // этаж
    private int room;               // комната

    ReadingRoom(String name, Address address, String city, Director director, int countLiterature, int floor, int room){
        this.name = name;
        this.address = address;
        this.city = city;
        this.director = director;
        this.countLiterature = countLiterature;
        this.floor = floor;
        this.room = room;
    }

    public int getCountLiterature() {
        return countLiterature;
    }

    public int getFloor() {
        return floor;
    }

    public int getRoom() {
        return room;
    }

    public String toString(){
        return "{\nname: " + this.name +
                "\ncount of literature: " + this.countLiterature +
                "\nfloor: " + this.floor +
                "\nroom: " + this.room +
                "\n}";
    }
}



class Reader extends Library{       // класс "читатель". Наследуется от класса "Библиотека"
    private Director readerName;    // ФИО читателя
    private Address addressWorking; // Адрес работы читателя
    private int age;                // возраст
    private String gender;          // пол

    Reader(String name, Address address, String city, Director director, Director readerName, Address addressWorking,
           int age, String gender){
        this.name = name;
        this.address = address;
        this.city = city;
        this.director = director;
        this.readerName = readerName;
        this.addressWorking = addressWorking;
        this.age = age;
        this.gender = gender;
    }

    public Address getAddressWorking() {
        return addressWorking;
    }

    public Director getReaderName() {
        return readerName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String toString(){
        return "{\nfull name: " + this.readerName +
                "\nwork's address: " + this.addressWorking +
                "\nage: " + this.age +
                "\ngender: " + this.gender +
                "\n}";
    }

}


class GetLiterature extends Library {       // класс "Выдача литературы". Наследуется от класса "Библиотека"
    private ReadingRoom readingRoom;        // параметр - объект "Читательский зал"
    private Reader reader;                  // параметр - объект "Читатель"
    private String nameLiterature;          // параметр - имя книги, которую взяли
    private String dateOfGetting;           // параметр - дата выдачи книги
    private String timeOfGetting;           // срок, на который выдали
    private int sum;                        // залог

    GetLiterature(String name, Address address, String city, Director director, ReadingRoom readingRoom, Reader reader,
                  String nameLiterature, String dateOfGetting, String timeOfGetting, int sum) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.director = director;
        this.readingRoom = readingRoom;
        this.reader = reader;
        this.nameLiterature = nameLiterature;
        this.dateOfGetting = dateOfGetting;
        this.timeOfGetting = timeOfGetting;
        this.sum = sum;
    }

    // методы, возвращающие параметры

    public int getSum() {
        return sum;
    }

    public Reader getReader() {
        return reader;
    }

    public ReadingRoom getReadingRoom() {
        return readingRoom;
    }

    public String getDateOfGetting() {
        return dateOfGetting;
    }

    public String getNameLiterature() {
        return nameLiterature;
    }

    public String getTimeOfGetting() {
        return timeOfGetting;
    }

    public String toString(){
        return "{\nreading room:" + this.readingRoom +
                "\nreader: " + this.reader +
                "\nliterature name: " + this.nameLiterature +
                "\ndate: " + this.dateOfGetting +
                "\ntime getting: " + this.timeOfGetting +
                "\nsum: " + this.sum +
                "\n}";
    }


}


