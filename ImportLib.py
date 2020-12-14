import argparse
import os
import re
import sys

parser = argparse.ArgumentParser("This tool can import a fasta file to the IgBlast database")
parser.add_argument("fastaFile", metavar='fastaFile', type=str, help="the path to the fasta file")
parser.add_argument("--name", "-n", dest="name", type=str, help="name of the database(default:the name of fasta file)")
parser.add_argument("--path", "-p", dest='database', type=str, help='the path to the database(default:./database)')

if __name__ == '__main__':
    args = parser.parse_args()
    fasta = args.fastaFile

    pattern = r'.*\.fasta$'
    if not re.match(pattern, fasta):
        sys.stderr.write("Error: fasta file supported only")
        sys.exit()

    name = args.name if args.name else fasta.split('/')[-1][:-6]
    db_path = args.database if args.database else "database"

    if os.system("mkdir {}/{}".format(db_path, name)):
        sys.stderr.write("Error: database already exists")
        sys.exit()

    edit_op = "./bin/edit_imgt_file.pl {} > {}/{}/{}".format(fasta, db_path, name, name)
    if os.system(edit_op):
        sys.exit()

    makedb_op = "./bin/makeblastdb -parse_seqids -dbtype prot -in {}/{}/{} -out {}/{}/{}".format(db_path, name, name,
                                                                                             db_path, name, name)
    if os.system(makedb_op):
        sys.exit()

    if os.system("cp {} {}/{}".format(fasta, db_path, name)):
        sys.exit()
