db = db.getSiblingDB('task-tracker');

db.users.insertMany([
    {
        _id: "user1",
        username: "john_doe",
        email: "john.doe@example.com"
    },
    {
        _id: "user2",
        username: "jane_smith",
        email: "jane.smith@example.com"
    }
]);

db.tasks.insertMany([
    {
        _id: "task1",
        name: "Initial Task 1",
        description: "This is the first initial task",
        createdAt: new Date(),
        updatedAt: new Date(),
        status: "TODO",
        authorId: "user1",
        assigneeId: "user2",
        observerIds: ["user1", "user2"]
    },
    {
        _id: "task2",
        name: "Initial Task 2",
        description: "This is the second initial task",
        createdAt: new Date(),
        updatedAt: new Date(),
        status: "IN_PROGRESS",
        authorId: "user2",
        assigneeId: "user1",
        observerIds: ["user1"]
    }
]);
