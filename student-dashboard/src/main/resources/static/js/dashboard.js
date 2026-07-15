const token = localStorage.getItem("token");

async function loadDashboard(){

    const response = await fetch("/api/dashboard",{

        headers:{
            Authorization:"Bearer "+token
        }

    });

    const data = await response.json();

    document.getElementById("totalStudents").innerText = data.totalStudents;
    document.getElementById("totalTasks").innerText = data.totalTasks;
    document.getElementById("totalAttendance").innerText = data.totalAttendance;
    document.getElementById("presentCount").innerText = data.presentCount;
    document.getElementById("absentCount").innerText = data.absentCount;

}

loadDashboard();