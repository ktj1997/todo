import { useState } from "react";

const Todo = (props) => {
  const { index, data, onChangeTodo, onDeleteTodo } = props;
  const [input, SetInput] = useState(data.name);

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
    <div className="flex justify-between items-center w-full px-4 py-2 my-2 text-gray-600 bg-gray-100 rounded">
      <div>
        <input
          type="checkbox"
          name="isChecked"
          className="m-2"
          onChange={onClickCheckBoxHandler}
          checked={data.isChecked}
        />
        <span className={data.isChecked ? "line-through" : undefined}>
          {input}
        </span>
      </div>
      <div className="items-center">
        <button
          className="px-4 py-2 float-right"
          onClick={onClickButtonHandler}
        >
          x
        </button>
      </div>
    </div>
  );
};

export default Todo;
