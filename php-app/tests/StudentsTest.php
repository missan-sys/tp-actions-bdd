<?php

use PHPUnit\Framework\TestCase;

require_once __DIR__ . '/../Students.php';

class StudentsTest extends TestCase
{
    protected function setUp(): void
    {
        $conn = new PDO(
            "pgsql:host=localhost;port=5432;dbname=tp",
            "postgres",
            "postgres"
        );

        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $conn->exec("TRUNCATE TABLE students RESTART IDENTITY");

        $conn->exec(
            "INSERT INTO students (name, grade)
             VALUES ('Amel', 15), ('Karim', 12)"
        );
    }

    public function testAddStudent(): void
    {
        Students::addStudent("Test", 18);

        $student = Students::getStudent(3);

        $this->assertNotNull($student);
        $this->assertEquals("Test", $student["name"]);
    }

    public function testGetStudent(): void
    {
        $student = Students::getStudent(1);

        $this->assertNotNull($student);
        $this->assertEquals("Amel", $student["name"]);
    }

    public function testAverageGrade(): void
    {
        $this->assertEquals(13.5, Students::averageGrade());
    }
}
