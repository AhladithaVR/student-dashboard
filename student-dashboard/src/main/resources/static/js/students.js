const token = localStorage.getItem("token");

let editingId = null;

// Load Students
async function loadStudents() {

    const response = await fetch("/api/students", {
        headers: {
            Authorization: "Bearer " + token
        }
    });

    const students = await response.json();

    let rows = "";

    students.forEach(student => {

        rows += `
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.email}</td>
            <td>${student.phone}</td>
            <td>${student.course}</td>
            <td>${student.year}</td>
            <td>${student.department}</td>

            <td>
                <button onclick="editStudent(${student.id})">Edit</button>
                <button onclick="deleteStudent(${student.id})">Delete</button>
            </td>
        </tr>
        `;

    });

    document.getElementById("studentTable").innerHTML = rows;
}

loadStudents();

document.getElementById("searchStudent").addEventListener("keyup", function () {

    const value = this.value.toLowerCase();

    const rows = document.querySelectorAll("#studentTable tr");

    rows.forEach(row => {

        row.style.display =
            row.innerText.toLowerCase().includes(value)
                ? ""
                : "none";

    });

});

// Add / Update Student
document.getElementById("studentForm").addEventListener("submit", async function (e) {

    e.preventDefault();

    const student = {

        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        course: document.getElementById("course").value,
        year: parseInt(document.getElementById("year").value),
        department: document.getElementById("department").value

    };

    let url = "/api/students";
    let method = "POST";

    if (editingId != null) {
        url = "/api/students/" + editingId;
        method = "PUT";
    }

    const response = await fetch(url, {

        method: method,

        headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + token
        },

        body: JSON.stringify(student)

    });

    if (response.ok) {

        alert(editingId == null ? "Student Added Successfully!" : "Student Updated Successfully!");

        editingId = null;

        document.getElementById("studentForm").reset();

        loadStudents();

    } else {

        alert("Operation Failed!");

    }

});


// Delete Student
async function deleteStudent(id) {

    if (!confirm("Are you sure you want to delete this student?")) {
        return;
    }

    const response = await fetch("/api/students/" + id, {

        method: "DELETE",

        headers: {
            Authorization: "Bearer " + token
        }

    });

    if (response.ok) {

        alert("Student Deleted Successfully!");

        loadStudents();

    } else {

        alert("Failed to Delete Student");

    }

}


// Edit Student
async function editStudent(id) {

    const response = await fetch("/api/students/" + id, {

        headers: {
            Authorization: "Bearer " + token
        }

    });

    const student = await response.json();

    editingId = id;

    document.getElementById("name").value = student.name;
    document.getElementById("email").value = student.email;
    document.getElementById("phone").value = student.phone;
    document.getElementById("course").value = student.course;
    document.getElementById("year").value = student.year;
    document.getElementById("department").value = student.department;

}
function exportCSV() {

    let csv = [];

    const rows = document.querySelectorAll("table tr");

    rows.forEach(row => {

        let cols = row.querySelectorAll("td, th");

        let data = [];

        cols.forEach(col => data.push(col.innerText));

        csv.push(data.join(","));

    });

    const blob = new Blob([csv.join("\n")], {
        type: "text/csv"
    });

    const link = document.createElement("a");

    link.href = URL.createObjectURL(blob);

    link.download = "students.csv";

    link.click();

}