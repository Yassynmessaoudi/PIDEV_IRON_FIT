@echo off
start cmd /k php C:\Users\MSI\ironfit2024\bin\console messenger:consume async --time-limit=3600
start cmd /k symfony serve