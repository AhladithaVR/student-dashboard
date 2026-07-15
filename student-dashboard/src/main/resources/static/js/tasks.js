const token = localStorage.getItem("token");

console.log("TASK PAGE TOKEN:", token);

let editingId = null;


// Load Tasks

async function loadTasks(){


const response = await fetch("/api/tasks",{

headers:{
Authorization:"Bearer "+token
}

});


const tasks = await response.json();


let rows="";


tasks.forEach(task=>{


rows += `

<tr>

<td>${task.id}</td>

<td>${task.taskTitle}</td>

<td>${task.description}</td>

<td>${task.status}</td>

<td>${task.dueDate}</td>


<td>

<button onclick="editTask(${task.id})">
Edit
</button>

<button onclick="deleteTask(${task.id})">
Delete
</button>

</td>

</tr>

`;

});


document.getElementById("taskTable").innerHTML=rows;


}


loadTasks();




// Add Task

document.getElementById("taskForm")
.addEventListener("submit",async function(e){


e.preventDefault();



const task = {
    taskTitle: document.getElementById("taskTitle").value,
    description: document.getElementById("description").value,
    status: document.getElementById("status").value,
    dueDate: document.getElementById("dueDate").value
};



const response = await fetch("/api/tasks",{


method:"POST",


headers:{


"Content-Type":"application/json",

Authorization:"Bearer "+token


},


body:JSON.stringify(task)


});



if(response.ok){


alert("Task Added Successfully");


document.getElementById("taskForm").reset();


loadTasks();


}

else{

    const errorText = await response.text();

    console.log("Backend Error:", errorText);

    alert("Failed: " + errorText);

}
});





// Delete Task

async function deleteTask(id){


if(!confirm("Delete this task?"))
return;



const response = await fetch("/api/tasks/"+id,{

method:"DELETE",

headers:{

Authorization:"Bearer "+token

}

});



if(response.ok){


alert("Deleted");

loadTasks();


}


}
async function editTask(id){

    const response = await fetch("/api/tasks/" + id, {

        headers:{
            Authorization:"Bearer " + token
        }

    });


    const task = await response.json();


    editingId = id;


    document.getElementById("taskTitle").value = task.taskTitle;

    document.getElementById("description").value = task.description;

    document.getElementById("status").value = task.status;

    document.getElementById("dueDate").value = task.dueDate;


}
async function editTask(id){

let title = prompt("Enter new task title");

if(!title)
return;


const task = {

taskTitle:title,
description:"",
status:"Pending",
dueDate:null

};


const response = await fetch("/api/tasks/"+id,{

method:"PUT",

headers:{
"Content-Type":"application/json",
Authorization:"Bearer "+token
},

body:JSON.stringify(task)

});


if(response.ok){

alert("Task Updated");
loadTasks();

}

}