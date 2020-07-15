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
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.sps.data.Project;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDateTime;
import java.time.Instant;
import java.util.TimeZone;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;


@WebServlet("/project")
public class ProjectServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Project> projects = new ArrayList<>();
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("project").addSort("dateCreated", SortDirection.DESCENDING);
        PreparedQuery results = datastore.prepare(query);

        for (Entity entity: results.asIterable()) {
            String title = (String) entity.getProperty("title");
            String summary = (String) entity.getProperty("summary");
            //String tags = (String) entity.getProperty("tags");
            long date = (long) entity.getProperty("dateCreated");
            LocalDateTime dateCreated = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), 
                                TimeZone.getDefault().toZoneId());
             
            projects.add(new Project(title, summary));
        }
        
        response.setContentType("application/json;");
        Gson gson = new Gson();
        String json = gson.toJson(projects);
        response.getWriter().println(json);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        //get params
        String title = getParameter(request, "title", "");
        String summary = getParameter(request, "summary", "");
       // HashSet tags = getParameter(request, "tags", ""); //returning as string

        Entity taskEntity = new Entity("project");
        taskEntity.setProperty("title", title);
        taskEntity.setProperty("summary", summary);
        //taskEntity.setProperty("tags", tags);
        taskEntity.setProperty("dateCreated", System.currentTimeMillis());
        datastore.put(taskEntity);

        response.sendRedirect("/projects.html"); 
    }

    private String getParameter(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getParameter(name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

}