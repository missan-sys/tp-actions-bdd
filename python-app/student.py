import psycopg2


def get_connection():
    return psycopg2.connect(
        host="localhost",
        database="tp",
        user="postgres",
        password="postgres",
        port="5432"
    )


def get_student(student_id):
    conn = get_connection()
    cursor = conn.cursor()

    cursor.execute(
        "SELECT id, name, grade FROM students WHERE id = %s",
        (student_id,)
    )

    student = cursor.fetchone()

    cursor.close()
    conn.close()

    return student


def average_grade():
    conn = get_connection()
    cursor = conn.cursor()

    cursor.execute("SELECT AVG(grade) FROM students")
    result = cursor.fetchone()

    cursor.close()
    conn.close()

    return float(result[0]) if result[0] is not None else 0
