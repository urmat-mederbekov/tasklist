const baseUrl = 'http://localhost:8080';

async function getTasks() {
    const response = await fetch(baseUrl + '/tasks');
    if(response.ok) {
        return response.json();
    }else {
        alert('Error: ' + response.error());
    }
}
async function loadTasks() {
    const tasks = getTasks();
    await tasks.then(taskData => {

        taskData.content.forEach((task) => {

            console.log(!document.getElementById(task.id));
            !document.getElementById(task.id)&&addTaskElement(createTaskElement(task));
            // addTaskElement(createTaskElement(task));
        });
    });
}
loadTasks();
const taskForm = document.getElementsByClassName("task-form")[0];
taskForm.addEventListener('submit', submitHandler);
function submitHandler(e) {
    e.preventDefault();
    const form = e.target;
    let data = new FormData(form);
    createTask(data);
    loadTasks();
}
async function createTask(taskFormData) {

    const taskJSON = JSON.stringify(Object.fromEntries(taskFormData));
    const settings = {
        method: 'POST',
        cache: 'no-cache',
        mode : 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: taskJSON
    };
    const response = await fetch(baseUrl + '/tasks', settings);
    const responseData = await response.json();
    console.log(responseData);
}
function addTaskElement(taskElement) {
    document.getElementsByClassName('task-list')[0].append(taskElement)
}
function createTaskElement(taskObject) {
    let taskList = document.createElement('li')
    taskList.id = `${taskObject.id}`;
    taskList.innerHTML =
        `<p>${taskObject.description}</p>`;
        // `<p >${taskObject.dateTime}</p>`;
    return taskList;
}