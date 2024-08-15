// Deleting the previous game area
function deleteArea() {
    const container = document.getElementById("gameField");

    while (container.firstChild) {
        container.removeChild(container.firstChild);
    }
}
// Creating snake game area
function newSnakeArea() {
    const area = document.getElementById("gameField");
    let newCanvas = document.createElement("canvas");
    newCanvas.setAttribute("id", "game");
    newCanvas.setAttribute("width", "608");
    newCanvas.setAttribute("height", "608");

    area.appendChild(newCanvas);
}

// Snake game
function startSnake() {
    deleteArea();
    newSnakeArea();

    const canvas = document.getElementById("game");
    const ctx = canvas.getContext("2d");

    const ground = new Image();
    ground.src = "/pictures/SnakeArea.png";

    const foodImg = new Image();
    foodImg.src = "/pictures/food.png";

    let box = 32;

    let score = 0;

    let food = {
        x: Math.floor((Math.random() * 17 + 1)) * box,
        y: Math.floor((Math.random() * 15 + 3)) * box
    };

    let snake = [];
    snake[0] = {
        x: 9 * box,
        y: 10 * box
    };

    document.addEventListener("keydown", direction);

    let dir;

    function direction(event) {
        if (event.keyCode == 37 && dir != "right")
            dir = "left";
        else if (event.keyCode == 38 && dir != "down")
            dir = "up";
        else if (event.keyCode == 39 && dir != "left")
            dir = "right";
        else if (event.keyCode == 40 && dir != "up")
            dir = "down";
    }

    function eatTale(head, arr) {
        for (let i = 0; i < arr.length; i++) {
            if (head.x == arr[i].x && head.y == arr[i].y) {
                clearInterval(game);
            }
        }
    }

    function drawGame() {
        ctx.clearRect(0, 0, 609, 609);
        ctx.drawImage(ground, 0, 0);

        ctx.drawImage(foodImg, food.x, food.y);

        for (let i = 0; i < snake.length; i++) {
            ctx.fillStyle = i == 0 ? "red" : "green";
            ctx.fillRect(snake[i].x, snake[i].y, box, box);
        }

        ctx.fillStyle = "white";
        ctx.font = "50px Arial";
        ctx.fillText(score, box * 2.5, box * 1.7);

        let snakeX = snake[0].x;
        let snakeY = snake[0].y;

        if (snakeX == food.x && snakeY == food.y) {
            score++;
            food = {
                x: Math.floor((Math.random() * 17 + 1)) * box,
                y: Math.floor((Math.random() * 15 + 3)) * box,
            };
        }
        else {
            snake.pop();
        }

        if (snakeX < box || snakeX > box * 17 || snakeY < box * 3 || snakeY > box * 17) {
            clearInterval(game);
        }

        if (dir == "left") snakeX -= box;
        if (dir == "right") snakeX += box;
        if (dir == "up") snakeY -= box;
        if (dir == "down") snakeY += box;

        let newHead = {
            x: snakeX,
            y: snakeY
        };

        eatTale(newHead, snake);

        snake.unshift(newHead);
    }

    let game = setInterval(drawGame, 100);
}

// Creating TicTacToe area
function newTicTacToeArea() {
    // Creating step field
    const field = document.getElementById("gameField");
    let newField = document.createElement("div");
    newField.setAttribute("class", "blockArea");
    newField.setAttribute("id", "blockArea");

    field.appendChild(newField);

    const elems = document.getElementById("blockArea");
    for (let i = 0; i < 9; i++) {
        let newElem = document.createElement("div");
        newElem.setAttribute("class", "blockItem");
        elems.appendChild(newElem);
    }
}

// TicTacToe
function startTicTacToe() { // TODO AI for game
    deleteArea();
    newTicTacToeArea();

    let step = "";
    let winner = "";

    const who =()=>{
        if (step == "circle") {
            step = "krest";
        }
        else {
            step = "circle";
        }
    }

    who()

    let blockItem = document.querySelectorAll(".blockItem");
    let counter = 0;

    blockItem.forEach((item)=>{
        item.addEventListener('click', ()=>{
            if (!item.classList.contains("circle") && !item.classList.contains("krest")) {
                item.classList.add(step);
                if (step == "krest") {
                    item.innerText = "X";
                }
                if (step == "circle") {
                    item.innerText = "O";
                }

                counter++;
                who()
                circleWin()
                krestWin()
                noWin()
            }
        })
    })

    let win = [
        [0, 1, 2],
        [0, 4, 8],
        [2, 4, 6],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8]
    ]

    let circleWin = ()=>{
        for (let i = 0; i < win.length; i++) {
            if (
                blockItem[win[i][0]].classList.contains("circle") &&
                blockItem[win[i][1]].classList.contains("circle") &&
                blockItem[win[i][2]].classList.contains("circle")
            ) {
                blockItem[win[i][0]].classList.add("winColor")
                blockItem[win[i][1]].classList.add("winColor")
                blockItem[win[i][2]].classList.add("winColor")
                winner = "Нулики";
                endGame(winner);
                return 1;
            }
        }
    }

    let krestWin = ()=>{
        for (let i = 0; i < win.length; i++) {
            if (
                blockItem[win[i][0]].classList.contains("krest") &&
                blockItem[win[i][1]].classList.contains("krest") &&
                blockItem[win[i][2]].classList.contains("krest")
            ) {
                blockItem[win[i][0]].classList.add("winColor")
                blockItem[win[i][1]].classList.add("winColor")
                blockItem[win[i][2]].classList.add("winColor")
                winner = "Крестики";
                endGame(winner);
                return 1;
            }
        }
    }

    let noWin = ()=>{
        if (!krestWin() && !circleWin() && (counter >= 9)) {
            winner = "Ничья";
            endGame(winner);
            for (let i = 0; i < win.length; i++) {
                for (let j = 0; j < 3; j++) {
                    blockItem[win[i][j]].classList.add("winColor");
                }
            }
        }
    }

    let blockArea = document.getElementById("blockArea");

    let endGame = (winner) =>{
        blockArea.style.pointerEvents = 'none';
    }
}

// New breakout area
function newBreakoutArea() {

}

// Breakout game
function breakout() { // TODO Breakout game
    deleteArea();
    newBreakoutArea();
}

// New asteroids area
function newAsteroidsArea() {

}

// Asteroids game
function asteroids() { // TODO Asteroids game
    deleteArea();
    newAsteroidsArea();
}