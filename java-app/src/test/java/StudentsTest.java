package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
public class StudentsTest {

    @BeforeEach
    void resetDatabase() throws Exception {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/tp",
                "postgres",
                "postgres");
             Statement stmt = conn.createStatement()) {

            stmt.execute("TRUNCATE TABLE students RESTART IDENTITY");
            stmt.execute(
                "INSERT INTO students (name, grade) VALUES " +
                "('Amel', 15), ('Karim', 12)"
            );
        }
    }

    @Test
    void testAddStudent() throws Exception {
        Students.addStudent("Test", 18);

        assertEquals("Test", Students.getStudent(3));
    }

    @Test
    void testGetStudent() throws Exception {
        assertEquals("Amel", Students.getStudent(1));
    }

    @Test
    void testAverageGrade() throws Exception {
        assertEquals(13.5, Students.averageGrade(), 0.001);
    }
}
