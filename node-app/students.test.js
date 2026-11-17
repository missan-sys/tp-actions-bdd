const {
    addStudent,
    getStudent,
    averageGrade,
    getConnection
} = require("./students");

beforeEach(async () => {
    const client = getConnection();
    await client.connect();

    await client.query("TRUNCATE TABLE students RESTART IDENTITY");
    await client.query(
        "INSERT INTO students (name, grade) VALUES ('Amel', 15), ('Karim', 12)"
    );

    await client.end();
});

test("Ajouter un étudiant", async () => {
    await addStudent("Test", 18);

    const student = await getStudent(3);

    expect(student).not.toBeNull();
    expect(student.name).toBe("Test");
});

test("Récupérer un étudiant", async () => {
    const student = await getStudent(1);

    expect(student).not.toBeNull();
    expect(student.name).toBe("Amel");
});

test("Calculer la moyenne", async () => {
    const average = await averageGrade();

    expect(average).toBe(13.5);
});
