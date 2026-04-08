# Run Task Manager Application
# Double-click or run in PowerShell from the project root

$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-25.0.2.10-hotspot"
$env:Path = "$env:Path;$env:JAVA_HOME\bin;$env:USERPROFILE\apache-maven\apache-maven-3.9.9\bin"

# Free port 8080 if already in use
$occupied = netstat -ano | findstr ":8080 "
if ($occupied) {
    $processPid = ($occupied -split '\s+' | Where-Object { $_ -match '^\d+$' } | Select-Object -Last 1)
    Write-Host "Freeing port 8080 (PID $processPid)..."
    taskkill /PID $processPid /F | Out-Null
}

Write-Host "Starting Task Manager..."
Write-Host "Open http://localhost:8080/tasks in your browser"
mvn spring-boot:run
