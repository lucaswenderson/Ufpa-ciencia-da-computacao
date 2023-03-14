import simpy

class Pessoas:
  def __init__(self, env, nome, tempEst, tempSoc):
    self.env = env
    self.nome = nome
    self.tempEst = tempEst
    self.tempSoc = tempSoc
    self.env.process(self.run())   
    

  def run(self):
    while True:
      print(self.nome,'Comecou estudar em',self.env.now)
      tempo_estudo = self.tempEst
      yield self.env.timeout(tempo_estudo)

      print(self.nome,'foi para as redes sociais em', self.env.now)
      tempo_redes_sociais = self.tempSoc
      yield self.env.timeout(tempo_redes_sociais)

class Carro:
  def __init__(self, env, nome, frequencia):
    self.frequencia = frequencia
    self.nome = nome
    self.env = env
    self.env.process(self.run())

  def run(self):
    while True:
      yield self.env.timeout(self.frequencia)
      print(self.nome,'Carro pasou em',self.env.now)

class Oficina:
  def __init__(self, env, tempo):
    self.env = env
    self.mecanicoDisp = self.env.event()
    self.env.process(self.chegouCarro())
    self.env.process(self.chegouMecanico(tempo))

  def chegouCarro(self):
    yield self.mecanicoDisp
    print('Desmonte do carro em',self.env.now)    

  def chegouMecanico(self, tempo):
    yield self.env.timeout(tempo)
    self.mecanicoDisp.succeed()

class Sair:
  def __init__(self, env, tVideoGame, tLigacao):
    self.env = env
    self.evVideoGame = self.env.event()
    self.evLigacao = self.env.event()
    self.env.process(self.decideSair())
    self.env.process(self.videoGame(tVideoGame))
    self.env.process(self.ligacao(tLigacao))

  def decideSair(self):
    yield self.evVideoGame | self.evLigacao
    print('Saiu em ',self.env.now)    

  def videoGame(self, tVideoGame):
    yield self.env.timeout(tVideoGame)
    self.evVideoGame.succeed()
    
  def ligacao(self, tLigacao):
    yield self.env.timeout(tLigacao)
    self.evLigacao.succeed()   

env = simpy.Environment()
o = Sair(env, 10, 20)
env.run()