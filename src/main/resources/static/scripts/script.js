$(async function () {
    await loadTasks();
    await addTask();
});

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
    const tasks = await getTasks();
    tasks.content.forEach(task => {

        !document.getElementById(task.id)&&
        addTaskElement(createTaskElement(task));
    });
    await complete();
}

const taskForm = document.getElementsByClassName("task-form")[0];
async function submitHandler(e) {
    e.preventDefault();
    const form = e.target;
    let data = new FormData(form);
    await createTask(data);
    await loadTasks();
    // await complete();

    taskForm.reset();
    taskForm.firstElementChild.focus();
}
async function addTask(){
    await taskForm.addEventListener('submit', submitHandler);
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
    document.getElementsByClassName('task-list')[0].append(taskElement);
}
function createTaskElement(taskObject) {
    let taskList = document.createElement('li');
    taskList.id = `${taskObject.id}`;
    taskList.innerHTML =
        `<p class="${taskObject.done}">${taskObject.description}</p>`;
    taskList.style.userSelect = 'none';
    if(taskList.firstElementChild.classList.contains("true")){
        taskList.firstElementChild.style.textDecoration = 'line-through';
    }
    return taskList;
}

async function complete() {

    for (let i = 0; i < document.getElementsByTagName('p').length; i++){
        let taskList = document.getElementsByTagName('p')[i];
        await taskList.addEventListener('dblclick',  async function (e) {

            const taskJSON = JSON.stringify(({id: taskList.parentElement.id}));
            const settings = {
                method: 'PUT',
                cache: 'no-cache',
                mode : 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: taskJSON
            };
            await fetch(baseUrl + '/tasks', settings);
            // await loadTasks();
            console.log(taskList.style.textDecoration);
            if(taskList.style.textDecoration === 'line-through'){
                unMark(e)
            }else{
                mark(e);
            }
        });
    }
}
function mark(e) {
    console.log('mark');
    const list = e.target;
    list.style.textDecoration = 'line-through';
}
function unMark(e) {
    console.log('unmark');
    const list = e.target;
    list.style.textDecoration = 'none currentcolor solid';
}