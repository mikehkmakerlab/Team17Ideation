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
  const liElement = document.createElement('li');
  liElement.innerText = project.title + ", " + project.summary + ", " + project.tags;
  return liElement;
}