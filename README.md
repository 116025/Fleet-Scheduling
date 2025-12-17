# 🚚⚡ Fleet Charging Scheduler

This project implements a scheduling algorithm that assigns electric trucks to available charging stations in a way that **maximizes the number of vehicles fully charged** within a fixed time window. 
## ✅ Problem Overview

A fleet yard contains:

- Multiple **trucks**, each with a battery capacity and current state of charge.
- Multiple **chargers**, each with a different charging rate.
- A limited charging window (e.g., overnight hours).

The objective is to:

> ✅ Determine **which trucks can be fully charged**  
> ✅ Assign them to chargers efficiently  
> ✅ Ensure no charger exceeds the available time  
> ✅ Maximize the total number of completed charges

Partial charging is **not** considered either a truck finishes within the window, or it is skipped.

---

## 🧠 Approach & Algorithm

This solution uses a **greedy scheduling strategy**:

1. Calculate how much energy each truck needs.
2. Estimate the minimum charging time for each truck across all chargers.
3. Sort trucks by the shortest required charging time (Shortest-Job-First heuristic).
4. For each truck, assign the charger that can finish it **earliest** without exceeding the time window.
5. Skip trucks that cannot be completed in time.

Test with input data
<img width="1359" height="767" alt="image" src="https://github.com/user-attachments/assets/74ffd546-daa7-4fc2-9e12-0807337e59cd" />

Out put
<img width="1359" height="767" alt="image" src="https://github.com/user-attachments/assets/acd3f33b-8531-4e2e-bc10-8a6b17518fed" />


