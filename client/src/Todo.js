import "./Todo.css";

const Todo = (props) => {
  const { index, data, onChangeTodo, onDeleteTodo } = props;

  const onClickCheckBoxHandler = (e) => {
    onChangeTodo(index, {
      ...data,
      isChecked: !data.isChecked
    });
  };
  const onClickButtonHandler = (e) => {
    onDeleteTodo(index);
  };
  return (
    <div className="Todo">
      <input
        type="checkbox"
        onChange={onClickCheckBoxHandler}
        checked={data.isChecked}
      />
      {data.name}
      <button onClick={onClickButtonHandler}>x</button>
    </div>
  );
};

export default Todo;
