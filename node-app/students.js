const { Client } = require("pg");

function getConnection() {
    return new Client({
        host: "localhost",
        database: "tp",
        user: "postgres",
        password: "postgres",
        port: 5432
    });
}

async function addStudent(name, grade) {
    const client = getConnection();
    await client.connect();

    await client.query(
        "INSERT INTO students (name, grade) VALUES ($1, $2)",
        [name, grade]
    );

    await client.end();
}

async function getStudent(id) {
    const client = getConnection();
    await client.connect();

    const result = await client.query(
        "SELECT id, name, grade FROM students WHERE id = $1",
        [id]
    );

    await client.end();
    return result.rows[0] || null;
}

async function averageGrade() {
    const client = getConnection();
    await client.connect();

    const result = await client.query(
        "SELECT AVG(grade) AS average FROM students"
    );

    await client.end();
    return Number(result.rows[0].average);
}

module.exports = { addStudent, getStudent, averageGrade, getConnection };
