# Description

The repository contains simple agent code with metrics collector for Windows, with no tests, no central node or frontend yet.

I've decided to push metrics from agents to the central node as agent nodes may be not accessible from central node by HTTP, and also this makes the agent simpler and smaller, as no servlet container needed.

The central node can control agents with SettingsDto in response to their requests. As for now, it contains pushing interval only, and this simple solution could produce load peaks on the central node. To resolve this, I'm planning to use cron-like expession instead of interval, and schedule push according to that.

To keep the agent executable jar small, and as it is very simple, I did not use any DI framework such as Spring, which I'm planning to use for the central node.

# Work log
## 2019-10-08
5h: Decide on agent -- central node communication, update local environment, implement simple Agent without tests and with Windows task metrics collector.

## 2019-10-09
30m: Introduce modular structure, extract API to a new module

## 2019-10-14
5h:
 * Improve project structure
 * Implement simple single-node "central node" API and and API for UI 
 * Describe multi-node scalable solution in comments
 * Add logging of requests to both Agent and Agency 
 * Cover metrics parsing with unit test
 * Improve metrics collection for Windows

## 2019-10-20
2h:
 * Started React UI with create-react-app-redux, abandoned it as it is too complicated for the task 
 * Implemented simple jquery-based UI
 
2h:
 * Tried to use Java 9's ProcessHandle, and it does not provide memory info
 * Used JProcesses for cross-platform metrics collecting
 * Improve code readability
 
 ## 2019-10-21
 
1h:
 * Implement basic auth for agent-api
 