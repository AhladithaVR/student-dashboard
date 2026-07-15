const token = localStorage.getItem("token");

let editingId = null;

// Load Attendance
async function loadAttendance() {

    const response = await fetch("/api/attendance", {

        headers: {
            Authorization: "Bearer " + token
        }

    });

    const attendance = await response.json();

    let rows = "";

    attendance.forEach(record => {

        rows += `
        <tr>
            <td>${record.id}</td>
            <td>${record.studentId}</td>
            <td>${record.studentName}</td>
            <td>${record.date}</td>
            <td>${record.status}</td>

            <td>
                <button class="edit-btn" onclick="editAttendance(${record.id})">Edit</button>
                <button class="delete-btn" onclick="deleteAttendance(${record.id})">Delete</button>
            </td>
        </tr>
        `;

    });

    document.getElementById("attendanceTable").innerHTML = rows;

}

loadAttendance();


// Add / Update Attendance

document.getElementById("attendanceForm").addEventListener("submit", async function(e){

    e.preventDefault();

    const attendance = {

        studentId: parseInt(document.getElementById("studentId").value),

        studentName: document.getElementById("studentName").value,

        date: document.getElementById("date").value,

        status: document.getElementById("status").value

    };

    let url="/api/attendance";
    let method="POST";

    if(editingId!=null){

        url="/api/attendance/"+editingId;

        method="PUT";

    }

    const response=await fetch(url,{

        method:method,

        headers:{

            "Content-Type":"application/json",

            Authorization:"Bearer "+token

        },

        body:JSON.stringify(attendance)

    });

    if(response.ok){

        alert(editingId==null?"Attendance Added":"Attendance Updated");

        editingId=null;

        attendanceForm.reset();

        loadAttendance();

    }

    else{

        alert("Operation Failed");

    }

});


// Delete

async function deleteAttendance(id){

    if(!confirm("Delete this attendance record?")) return;

    const response=await fetch("/api/attendance/"+id,{

        method:"DELETE",

        headers:{

            Authorization:"Bearer "+token

        }

    });

    if(response.ok){

        alert("Attendance Deleted");

        loadAttendance();

    }

}


// Edit

async function editAttendance(id){

    const response=await fetch("/api/attendance/"+id,{

        headers:{

            Authorization:"Bearer "+token

        }

    });

    const attendance=await response.json();

    editingId=id;

    document.getElementById("studentId").value=attendance.studentId;

    document.getElementById("studentName").value=attendance.studentName;

    document.getElementById("date").value=attendance.date;

    document.getElementById("status").value=attendance.status;

}