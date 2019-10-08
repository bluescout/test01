# test01

The repository contains simple agent code, with no tests, no central node or frontend yet. As for now, I've spent about 5h gross, including env setup.

I've decided to push metrics from agents to the central node as agent nodes may be not accessible from central node by HTTP, and also this makes the agent simpler and smaller, as no servlet container needed.

The central node can control agents with SettingsDto in response to their requests. As for now, it contains pushing interval only, and this simple solution could produce load peaks on the central node. To resolve this, I'm planning to use cron-like expession instead of interval, and schedule push according to that.

To keep the agent executable jar small, and as it is very simple, I did not use any DI framework such as Spring, which I'm planning to use for the central node.
