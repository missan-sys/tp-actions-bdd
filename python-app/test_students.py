from student import add_student, get_student, average_grade


def test_add_student():
    add_student("Test", 18)

    student = get_student(3)

    assert student is not None
    assert student[1] == "Test"


def test_get_student():
    student = get_student(1)

    assert student is not None
    assert student[1] == "Amel"


def test_moyenne():
    moyenne = average_grade()

    assert float(moyenne) == 13.5
