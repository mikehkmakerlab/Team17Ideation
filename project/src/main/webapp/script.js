// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


function getFilterProjects() {  
    fetch('/project')
    .then(response => response.json()).then((projects) => {
    const projectElement = document.getElementById('project-list');
    projects.forEach((project) => {
      projectElement.appendChild(createListElement(project));
    })
    });
}

/** Creates an <li> element containing text. */
function createListElement(project) {
  const liElement = document.createElement("div");
  liElement.className = 'container';
  
  const titleProject  = document.createElement('b');
  titleProject.innerText = project.title + ":";
  
  const descriptionProject = document.createElement('p');
  descriptionProject.innerText = project.summary;

  const tags = document.createElement('div');
  tags.className = 'tags';
  tags.innerText = 'tags: ' + project.tags;

  liElement.appendChild(titleProject);
  liElement.appendChild(descriptionProject);
  liElement.appendChild(tags);
  return liElement;
}

/**accordion */
document.addEventListener('DOMContentLoaded', function() { 
    var accItem = document.getElementsByClassName("accordionItem");
    var accHD = document.getElementsByClassName("accordionItemHeading");
    for (i = 0; i < accHD.length; i++) {
    accHD[i].addEventListener("click", toggleItem, false);
    }
    function toggleItem() {
    var itemClass = this.parentNode.className;
    for (i = 0; i < accItem.length; i++) {
        accItem[i].className = "accordionItem close";
    }
    if (itemClass == "accordionItem close") {
        this.parentNode.className = "accordionItem open";
    }
    }
});


/*
function userLoggedIn(){
    var loggedIn = false;
    fetch('/login').then(response => response.json()).then((authentication) => {
        loggedIn = authentication.status;

        if(loggedIn){
            document.getElementById("login").innerHTML = "<p>Logout <a href=\"" + authentication.logout + "\">here</a>.</p>";  
        } else {
            document.getElementById("login").innerHTML = "<p>Login <a href=\"" + authentication.logins + "\">here</a>.</p>";
        }

    });
}
*/