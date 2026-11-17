<?php

class Students
{
    private static function getConnection(): PDO
    {
        return new PDO(
            "pgsql:host=localhost;port=5432;dbname=tp",
            "postgres",
            "postgres",
            [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]
        );
    }

    public static function addStudent(string $name, float $grade): void
    {
        $conn = self::getConnection();

        $stmt = $conn->prepare(
            "INSERT INTO students (name, grade) VALUES (?, ?)"
        );

        $stmt->execute([$name, $grade]);
    }

    public static function getStudent(int $id): ?array
    {
        $conn = self::getConnection();

        $stmt = $conn->prepare(
            "SELECT id, name, grade FROM students WHERE id = ?"
        );

        $stmt->execute([$id]);

        $student = $stmt->fetch(PDO::FETCH_ASSOC);

        return $student ?: null;
    }

    public static function averageGrade(): float
    {
        $conn = self::getConnection();

        $result = $conn->query(
            "SELECT AVG(grade) FROM students"
        )->fetchColumn();

        return $result !== null ? (float) $result : 0;
    }
}

