function search() {
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;

    axios.get("/list/search", {
        params: {
            firstName: firstName,
            lastName: lastName
        }
    })
        .then(function (response) {
            let results = response.data;
            let resultsContainer = document.getElementById("results");
            resultsContainer.innerHTML = "";

            for (let i = 0; i < results.length; i++) {
                let person = results[i];
                let card = createCard(person);
                resultsContainer.appendChild(card);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

function createCard(person) {
    let card = document.createElement("div");
    card.classList.add("card", "mt-2");

    let img = document.createElement("img");
    img.src = "/icon/Icon_Person.png";
    img.classList.add("card-img-top");
    img.alt = "Изображение работника";
    card.appendChild(img);

    let cardBody = document.createElement("div");
    cardBody.classList.add("card-body");
    card.appendChild(cardBody);

    let title = document.createElement("h3");
    title.classList.add("card-title");
    title.textContent = person.firstName + " " + person.middleName + " " + person.lastName;
    cardBody.appendChild(title);

    let details = document.createElement("p");
    cardBody.appendChild(details);

    let phoneNumber = document.createElement("span");
    phoneNumber.classList.add("card-text");
    phoneNumber.textContent = "Телефон: " + person.phoneNumber;
    details.appendChild(phoneNumber);
    details.appendChild(document.createElement("br"));

    let position = document.createElement("span");
    position.classList.add("card-text");
    position.textContent = "Должность: " + person.position + " " + person.officeNumber;
    details.appendChild(position);
    details.appendChild(document.createElement("br"));

    return card;
}
