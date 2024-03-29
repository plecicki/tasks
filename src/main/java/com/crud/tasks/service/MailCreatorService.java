package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://plecicki.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Thank you for using CRUD App. See you next time :)");
        context.setVariable("company",
                companyConfig.getCompanyName() + "\n" +
                        companyConfig.getCompanyGoal() + "\n" +
                        companyConfig.getCompanyPhone() + "\n" +
                        companyConfig.getCompanyEmail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String dailyTaskCountEmail(String message) {
        long taskCount = taskRepository.count();
        boolean evenNumber = (taskCount % 2 == 0);

        List<Task> allTasks = taskRepository.findAll();

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("even_number", evenNumber);
        context.setVariable("tasks_url", "https://plecicki.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye", "Thank you for using CRUD App. See you next time :)");
        context.setVariable("company",
                companyConfig.getCompanyName() + "\n" +
                        companyConfig.getCompanyGoal() + "\n" +
                        companyConfig.getCompanyPhone() + "\n" +
                        companyConfig.getCompanyEmail());
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", true);
        context.setVariable("tasks", allTasks);
        return templateEngine.process("mail/daily-mail", context);
    }
}
