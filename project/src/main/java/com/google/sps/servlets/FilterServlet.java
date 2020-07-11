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

package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.Project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/filter")
public class FilterServlet extends HttpServlet {

    private List<Project> projects;

    @Override
    public void init() {
        projects = new ArrayList<>();

        Project snake = new Project("Snake");
        snake.setSummary("Making snake game in python.");
        snake.addTag("python");
        snake.addTag("backend");
        projects.add(snake);

        Project coffee = new Project("Coffee");
        coffee.setSummary("Barista game in java.");
        coffee.addTag(" Java ");
        coffee.addTag("backend");
        projects.add(coffee);

        Project hamster = new Project("Hamster");
        hamster.setSummary("Website on hamster care.");
        hamster.addTag(" JavaScript");
        hamster.addTag("frontend");
        projects.add(hamster);

        Project andromeda = new Project("Andromeda");
        andromeda.setSummary("Spaceeeeee simulator website.");
        andromeda.addTag("python");
        andromeda.addTag("backend");
        andromeda.addTag("frontend");
        projects.add(andromeda);

        Project portfolio = new Project("Portfolio");
        portfolio.setSummary("Art museum's portfolio.");
        portfolio.addTag("HTML");
        portfolio.addTag("Javascript ");
        portfolio.addTag("java");
        projects.add(portfolio);

        Project money = new Project("Stocks");
        money.setSummary("Website that analyzes and displays stock market fluctuations.");
        money.addTag(" Python");
        money.addTag(" backend");
        money.addTag(" html ");
        money.addTag("CSS");
        projects.add(money);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //would get parameter from user... hard coded for now
        String tag = "python";

        List<Project> filtered = projects
            .stream()
            .filter(proj -> proj.getTags().contains(tag))
            .collect(Collectors.toList());
        
        response.setContentType("application/json;");
        Gson gson = new Gson();
        String json = gson.toJson(filtered);
        response.getWriter().println(json);
    }

}