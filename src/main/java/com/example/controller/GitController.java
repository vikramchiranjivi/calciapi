package com.example.controller;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.RefSpec;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GitController {

    @GetMapping("/push")
    public String pushPage() {
        return "redirect:/push.html"; // This will look for push.html in src/main/resources/static
    }

    @PostMapping("/push-to-github")
    @ResponseBody
    public Map<String, String> pushToGitHub(@RequestBody Map<String, String> payload) {
        String commitMessage = payload.get("commitMessage"); // Get the commit message from the request
        Map<String, String> response = new HashMap<>();

        try {
            // Path to your STS project (adjust the path to your local project directory)
            String repoPath = "C:\\Users\\harih\\git\\calciapi"; // Replace with actual path

            // Open the Git repository
            Git git = Git.open(new File(repoPath));

            // Ensure we are on the master branch
            if (!git.getRepository().getBranch().equals("master")) {
                response.put("error", "You are not on the master branch.");
                return response;
            }

            // Step 1: Pull the latest changes from origin/master
            PullCommand pull = git.pull().setRemote("origin").setRemoteBranchName("master");
            pull.call();

            // Step 2: Add all changes
            git.add().addFilepattern(".").call();

            // Step 3: Commit with the provided message
            git.commit().setMessage(commitMessage).call();

            // Step 4: Push to the master branch
            RefSpec refSpec = new RefSpec("refs/heads/master:refs/heads/master");
            PushCommand pushCommand = git.push();
            pushCommand.setRemote("origin").setRefSpecs(refSpec).call();

            response.put("message", "Pull, commit, and push to GitHub successful on master branch.");
        } catch (GitAPIException | IOException e) {
            response.put("error", "Operation failed: " + e.getMessage());
        }

        return response;
    }
}
