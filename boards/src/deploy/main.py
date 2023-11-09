import sys

from color_print import print_console, Colors


def print_help():
    print_console("Auto Arduino CLI\n")
    print_console(" -m monitor")
    print_console(" -c compile")
    print_console(" -u upload")
    print_console(" -b specify board")


def unknown_argument(arg):
    print_console("Unknown argument: ", endl="")
    print_console(arg, Colors.FAIL)
    exit()


FUNCTION        = "function"
NUMBER_OF_ITEMS = "number_of_items"
NEEDS           = "needs"


def interpret_args():

    args = {
        "-m" : { FUNCTION : monitor,     NUMBER_OF_ITEMS : 0, NEEDS : ["-b"] },
        "-c" : { FUNCTION : compile_src, NUMBER_OF_ITEMS : 0, NEEDS : ["-b"] },
        "-u" : { FUNCTION : upload,      NUMBER_OF_ITEMS : 0, NEEDS : ["-b"] },
        "-b" : { FUNCTION : board,       NUMBER_OF_ITEMS : 1, NEEDS : [ ]   }
    }

    input_args        = {}
    curr_arg          = None
    n_values_counter  = 0
    for arg in sys.argv[1:]:
        if n_values_counter > 0:        # next string is a value for one argument
            input_args[curr_arg].append(arg)
            n_values_counter -= 1
        elif arg not in args.keys():
            unknown_argument(arg)
        else:
            input_args[arg]  = []
            curr_arg         = arg
            n_values_counter = args[arg][NUMBER_OF_ITEMS]

    if n_values_counter > 0:
        print_console(f"Value for argument {curr_arg} is missing.")
        exit()

    for arg, values in input_args.items():
        for need in args[arg][NEEDS]:
            if need not in input_args.keys():
                print_console(f" -{need} argument is needed to run {arg} command")


def monitor():
    pass


def compile_src():
    pass


def upload():
    pass


def board():
    pass


if __name__ == '__main__':
    interpret_args()





